package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.UserContract
import com.unimind.unithing.Repository.UserRemoteDataSource.UserRepositoryImpl

class UserPresenter(val view: UserContract.View) : UserContract.Presenter {
    override fun requestCreateDB() {
        UserRepositoryImpl.createCertificateDB { isSuccess ->
            if (isSuccess) {
                view.nextActivity()
            } else {
                view.showToast("message")
            }
        }
    }
}