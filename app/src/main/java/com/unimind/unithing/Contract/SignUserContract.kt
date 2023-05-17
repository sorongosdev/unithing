package com.unimind.unithing.Contract

interface SignUserContract {
    interface View {
        fun showToast(message: String)
        fun nextActivity()
    }

    interface Presenter {
        fun requestSignUp(userId: String, userPassword: String)
    }

}