package com.unimind.unithing.Contract

interface SignUserContract {
    interface View {
        fun showToast(message: String)
        fun nextActivity()
    }

    interface Presenter {
        fun requestSignUp(userEmail: String, userPassword: String)
        fun requestSignIn(userEmail: String, userPassword: String)

    }

}