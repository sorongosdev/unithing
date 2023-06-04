package com.unimind.unithing.Presenter

import android.graphics.Bitmap
import com.unimind.unithing.Contract.UserContract
import com.unimind.unithing.Repository.UserRemoteDataSource.UserRepositoryImpl

class UserPresenter(val view: UserContract.View) : UserContract.Presenter {
    override fun requestCreateDB() {
        UserRepositoryImpl.createCertificateDB { isSuccess ->
            if (isSuccess) {
                //view.nextActivity()
            } else {
                view.showToast("message")
            }
        }
    }

    override fun requestUploadImg(image: Bitmap) {
        UserRepositoryImpl.uploadStorage(image) {
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
}