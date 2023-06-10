package com.unimind.unithing

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import com.unimind.unithing.Contract.CertificateContract
import com.unimind.unithing.Presenter.CertificatePresenter
import com.unimind.unithing.databinding.ActivityCertificationBinding

class CertificationActivity : AppCompatActivity(), CertificateContract.View {
    private lateinit var binding: ActivityCertificationBinding
    private lateinit var presenter: CertificatePresenter

    // 카메라 호출을 위한 상수
    private val REQUEST_IMAGE_CAPTURE = 1

    // 갤러리 라이브러리 관련 프로퍼티
    val launcher = registerImagePicker { result: List<Image> ->
        result.forEach { image ->
            val imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image.uri)

            Toast.makeText(
                this@CertificationActivity,
                "이미지 가져옴.",
                Toast.LENGTH_SHORT
            )
                .show()

            setImageView(imageBitmap)
            presenter.recognizeFromImage(imageBitmap)
            presenter.requestUploadImg(imageBitmap)
        }
    }

    // 갤러리 라이브러리 관련 프로퍼티 2
    val config = ImagePickerConfig {
        mode = ImagePickerMode.SINGLE // default is multi image mode
        isIncludeVideo = false // 영상 제외하고 이미지만 불러옴
        isFolderMode = true // false로 설정시  폴더 선택 불가능. 모든 사진
        isShowCamera = false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_certification)
        presenter = CertificatePresenter(this)

        binding.activityCertificationBtn1.setOnClickListener {
            presenter.requestCreateDB()
        }

        binding.activityCertificationCameraBtn.setOnClickListener {
            presenter.requestPermissions("Camera")
        }

        binding.activityCertificationGalleryBtn.setOnClickListener {
            presenter.requestPermissions("Gallery")
        }
    }


    override fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun openGallery() {
        launcher.launch(config)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            setImageView(imageBitmap)
            presenter.recognizeFromImage(imageBitmap)
            presenter.requestUploadImg(imageBitmap)
        }
    }


    override fun setImageView(resource: Bitmap) {
        Glide.with(this)
            .load(resource)
            .into(binding.activityCertificationIv)
    }

    override fun setText(text: String) {
        binding.activityCertificationRecognizedMajorTv.setText(text)
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
