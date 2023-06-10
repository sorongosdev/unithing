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

    interface SignUserRepository {
        fun requestSignUp(email: String, password: String,type: String, callback: (Boolean, String?) -> Unit)
        fun requestSignIn(email: String, password: String, callback: (Boolean, String?) -> Unit)
    }

}