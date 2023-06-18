package com.unimind.unithing.Contract

import com.unimind.unithing.Data.User

interface UserInfoContract {
    interface View{
        /**권한이 있을 때 동작*/
        fun isAuthorized()
        /**권한이 없을 때 동작*/
        fun notAuthorized()
    }
    interface Presenter{

    }
    interface UserInfoRepository{
        fun insertUserInfo(user: User)
        fun findUser(userUid: String)
    }
}