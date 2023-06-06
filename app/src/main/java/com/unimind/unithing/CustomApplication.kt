package com.unimind.unithing

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes






class CustomApplication : Application() {
    override fun onCreate() {
        ctx_storage = this
        super.onCreate()
    }

    companion object {
        var ctx_storage: Context? = null
        // context를 사용하지 않는 클래스에서(액티비티, 프래그먼트와 같은 뷰를 제외한 클래스에서) context를 사용하기 위함. ex) Strings.xml 문자열 참조
        val ctx: Context?
            get() = ctx_storage
    }
}


object StringResource {
    fun getStringResource(context: Context?, @StringRes id: Int): String {
        return context?.getString(id) ?: "null"
    }
}