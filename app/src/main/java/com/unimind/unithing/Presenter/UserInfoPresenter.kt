package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Data.User
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl


class UserInfoPresenter(val view: UserInfoContract.View): UserInfoContract.Presenter{
    override fun getAuthority() {
        //권한있음
        //null이면 계속 시도

        if(UserInfoRepositoryImpl.currentUser?.authorized == true){
            view.isAuthorized()
        } else view.notAuthorized()
    }

}