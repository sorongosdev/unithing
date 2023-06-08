package com.unimind.unithing.Contract

interface SignUserContract {
    interface View {
        fun showToast(message: String)
        fun nextActivity()
        fun showValidation(errorMsg : String?)
//        fun checkValidation()

    }

    interface Presenter {
        fun requestSignUp(userEmail: String, userPassword: String, userType: String)
        fun requestSignIn(userEmail: String, userPassword: String)
        fun checkValidation(email: String)
    }

}