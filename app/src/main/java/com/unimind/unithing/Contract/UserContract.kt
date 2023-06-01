package com.unimind.unithing.Contract

interface UserContract {

    interface View {
        fun showToast(message: String)
        fun nextActivity()
    }

    interface Presenter {
        fun requestCreateDB()
    }
}