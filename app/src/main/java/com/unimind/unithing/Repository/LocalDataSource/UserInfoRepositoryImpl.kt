package com.unimind.unithing.Repository.LocalDataSource

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unimind.unithing.AppDatabase
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.User
/**로컬에 유저정보를 저장하는 싱글톤 객체 : 권한에 따른 뷰 생성, 마이페이지에서 유저정보를 보여주기 위해 필요함 */
object UserInfoRepositoryImpl {
    fun saveUserInfo(user: User) {
        Log.d("saveUserInfo","${user}")
        //TODO : Room에 저장
    }
}