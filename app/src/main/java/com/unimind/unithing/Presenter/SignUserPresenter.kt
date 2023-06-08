package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.SignUserContract
import com.unimind.unithing.Repository.SignUserRemoteDataSource.SignUserRepositoryImpl

class SignUserPresenter(val view: SignUserContract.View): SignUserContract.Presenter {

    override fun requestSignUp(userEmail: String, userPassword: String, userType: String) {
        SignUserRepositoryImpl.requestSignUp(userEmail, userPassword, userType) {
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

    /**뷰를 띄워줌*/
    override fun checkValidation(email: String) {
        SignUserRepositoryImpl.checkValidation(email){ errorEmail ->
            if (errorEmail){
                view.showValidation("잘못된 이메일 형식")
            }
            else {
                view.showValidation(null)
            }
        }
    }

}