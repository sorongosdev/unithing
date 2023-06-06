package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.SignUserContract
import com.unimind.unithing.Repository.SignUserRemoteDataSource.SignUserRepositoryImpl

class SignUserPresenter(val view: SignUserContract.View): SignUserContract.Presenter {

    override fun requestSignUp(userEmail: String, userPassword: String) {
        SignUserRepositoryImpl.requestSignUp(userEmail, userPassword) {
            isSuccess, errorMsg ->
            if (isSuccess) {
                view.nextActivity()
            } else {
                view.showToast("회원가입 실패 ${errorMsg}")
            }
        }
    }

    override fun requestSignIn(userEmail: String, userPassword: String) {
        SignUserRepositoryImpl.requestSignIn(userEmail, userPassword) {
                isSuccess, errorMsg ->
            if (isSuccess) {
                view.nextActivity()
            } else {
                view.showToast("로그인 실패 ${errorMsg}")
            }
        }
    }

    override fun checkValidation(email: String) {
        SignUserRepositoryImpl.checkValidation(email){ errorMsg->
            if (errorMsg != null){
                view.showToast("wrong")
            }
            else {
                view.showToast("great")
            }
        }
    }

}