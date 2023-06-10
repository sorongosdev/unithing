package com.unimind.unithing.Presenter

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.unimind.unithing.Contract.CertificateContract
import com.unimind.unithing.Repository.RemoteDataSource.CertificateRepositoryImpl
import java.io.IOException

class CertificatePresenter(val view: CertificateContract.View) : CertificateContract.Presenter {
    override fun requestCreateDB() {
        CertificateRepositoryImpl.createCertificateDB { isSuccess ->
            if (isSuccess) {
                //view.nextActivity()
            } else {
                view.showToast("message")
            }
        }
    }

    override fun requestUploadImg(image: Bitmap) {
        CertificateRepositoryImpl.uploadStorage(image) {
                isSuccess ->
            if (isSuccess) {
                view.showToast("이미지 업로드 성공")
               // view.nextActivity()
            } else {
                view.showToast("이미지 업로드 실패")

              //  view.showToast("message")
            }
        }
    }

    override fun recognizeFromImage(img: Bitmap) {
        try {
            val image = InputImage.fromBitmap(img, 0)
            //val image = InputImage.fromFilePath(this, img)
            //val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val recognizer =
                TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
            recognizer.process(image)
                .addOnSuccessListener {
                    view.setText(it.text)
                    Log.d("recognized", it.text.toString())
                }
                .addOnFailureListener {

                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    override fun requestPermissions(type: String) {
        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    if (type == "Camera") {
                        view.openCamera()
                    } else if (type == "Gallery") {
                        view.openGallery()
                    }
                }

                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    view.showToast("권한을 허가해주세요. ")
                }
            })

            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            .check()
    }
}