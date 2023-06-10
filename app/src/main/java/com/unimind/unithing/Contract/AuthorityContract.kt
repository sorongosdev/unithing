package com.unimind.unithing.Contract

import android.graphics.Bitmap

interface AuthorityContract {
    interface View{
        fun showToast(message: String)
        fun nextActivity()
        /**권한이 있을 때 동작*/
        fun isAuthorized()
        /**권한이 없을 때 동작*/
        fun notAuthorized()
    }
    interface Presenter {
        /**파이어베이스에서 받아온 것으로부터 권한을 검사하는 로직*/
        fun showAuthority()
    }

    interface AuthorityRepository {
        /**파이어베이스로부터 해당 유저의 권한을 받아옴*/
        fun getAuthority(callback: (Boolean) -> Unit)
    }
}