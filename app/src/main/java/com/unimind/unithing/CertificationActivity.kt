package com.unimind.unithing

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.IpCons.RC_IMAGE_PICKER
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.createImagePickerIntent
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.unimind.unithing.Contract.UserContract
import com.unimind.unithing.Presenter.UserPresenter
import com.unimind.unithing.databinding.ActivityCertificationBinding

class CertificationActivity : AppCompatActivity(), UserContract.View {
    private lateinit var binding : ActivityCertificationBinding
    private lateinit var presenter : UserPresenter

    // 카메라 호출을 위한 상수
    private val REQUEST_IMAGE_CAPTURE = 1

    // 갤러리 라이브러리 관련 프로퍼티
    val launcher = registerImagePicker { result: List<Image> ->
        result.forEach { image ->

            setImageView(MediaStore.Images.Media.getBitmap(getContentResolver(), image.uri))


            Toast.makeText(this@CertificationActivity,
                "이미지 가져옴.",
                Toast.LENGTH_SHORT)
                .show()

            presenter.requestUploadImg(MediaStore.Images.Media.getBitmap(getContentResolver(), image.uri))
        }
    }

    // 갤러리 라이브러리 관련 프로퍼티 2
    val config = ImagePickerConfig {
        mode = ImagePickerMode.SINGLE // default is multi image mode
        isIncludeVideo = false // 영상 제외하고 이미지만 불러옴
        isFolderMode = true // false로 설정시  폴더 선택 불가능. 모든 사진
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_certification)
        presenter = UserPresenter(this)

        binding.activityCertificationBtn1.setOnClickListener {
            presenter.requestCreateDB()

        }

        binding.activityCertificationCameraBtn.setOnClickListener {
            requestPermission {
                openCamera()
            }
        }

        binding.activityCertificationGalleryBtn.setOnClickListener {
            requestPermission {
                launcher.launch(config)
            }

        }


    }


    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            setImageView(imageBitmap)
            presenter.requestUploadImg(imageBitmap)
        }
    }


    fun setImageView(resource: Bitmap) {
        Glide.with(this)
            .load(resource)
            .into(binding.activityCertificationIv)
    }
    // 권한 관련 코드
    private fun requestPermission(logic : () -> Unit){
        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    logic()
                }
                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    Toast.makeText(this@CertificationActivity,
                        "권한을 허가해주세요.",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,)
            .check()
    }



    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun nextActivity() {
        this?.finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}