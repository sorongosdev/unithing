package com.unimind.unithing.Presenter

import android.util.Log
import android.util.Patterns
import com.unimind.unithing.Contract.SignUserContract
import com.unimind.unithing.Data.User
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.SignUserRepositoryImpl

class SignUserPresenter(val view: SignUserContract.View): SignUserContract.Presenter {

    override fun requestSignUp(userEmail: String, userPassword: String, userType: String) {
        SignUserRepositoryImpl.requestSignUp(userEmail, userPassword, userType) {
            isSuccess, errorMsg ->
            if (isSuccess) {
                //user정보를 firestore에서 받아와 로컬에 저장
                val user = User()
                SignUserRepositoryImpl.getUserInfo(user){
                    UserInfoRepositoryImpl.insertUserInfo(it)
                }
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
                val user = User()
                SignUserRepositoryImpl.getUserInfo(user){
                    Log.d("Appdatabase", "insertUserInfo 호출")
                    UserInfoRepositoryImpl.insertUserInfo(it)
                }
                view.nextActivity()
            } else {
                view.showToast("로그인 실패 ${errorMsg}")
            }
        }
    }

    /**뷰를 띄워줌*/
    override fun checkValidation(email: String) {
        val pattern = Patterns.EMAIL_ADDRESS
        if (pattern.matcher(email).matches()) {
            view.showValidation(null)
        }
        else view.showValidation("잘못된 이메일 형식") // 이메일 오류
    }
}