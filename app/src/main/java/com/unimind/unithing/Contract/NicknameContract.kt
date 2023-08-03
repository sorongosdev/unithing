package com.unimind.unithing.Contract

import com.unimind.unithing.Data.User

interface NicknameContract {
    interface View{

    }
    interface Presenter{
        fun makeRandomId(): String
    }
    interface NicknameRepository{
    }
}