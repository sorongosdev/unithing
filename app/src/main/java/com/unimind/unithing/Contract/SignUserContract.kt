package com.unimind.unithing.Contract

interface SignUserContract {
    interface View {
        fun showToast(message: String)
        fun nextActivity()
//        fun checkValidation()
        fun showValidation(errorMsg : String?)
    }

    interface Presenter {
        fun requestSignUp(userEmail: String, userPassword: String)
        fun requestSignIn(userEmail: String, userPassword: String)
        fun checkValidation(email: String)
    }

}