package com.unimind.unithing.Contract

import android.graphics.Bitmap

interface AuthorityContract {
    interface View{
        fun showToast(message: String)
        fun nextActivity()
    }
    interface Presenter {
        /**파이어베이스에서 받아온 것으로부터 권한을 검사하는 로직*/
        fun checkAuthority()
    }

    interface CertificateRepository {
        /**파이어베이스로부터 해당 유저의 권한을 받아옴*/
        fun getAuthority()
    }
}