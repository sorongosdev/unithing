package com.unimind.unithing.Repository.LocalDataSource

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unimind.unithing.AppDatabase
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.User

/**로컬에 유저정보를 저장하는 싱글톤 객체 : 권한에 따른 뷰 생성, 마이페이지에서 유저정보를 보여주기 위해 필요함 */
object UserInfoRepositoryImpl : UserInfoContract.UserInfoRepository{
    lateinit var currentUser: User

    /**로컬에 유저정보 저장, 회원가입 시 한번만 실행*/
    override fun insertUserInfo(user: User) {
        Thread {
            AppDatabase.getInstance(CustomApplication.ctx!!)?.userDao()?.insert(user)
        }.start()
    }

    /**로그인될 때 회원정보를 업데이트, 이후 닉네임 변경, 과 변경 등등 변경 관련 함수 생기면 리네이밍 필요*/
    override fun updateUserInfo(user: User){
        Thread {
            AppDatabase.getInstance(CustomApplication.ctx!!)?.userDao()?.update(user)
            Log.d("updateUserInfo","$user")
        }.start()
    }

    /**room db에서 현재 current user을 찾음*/
    override fun findUser(userUid: String) {
        Thread {
            val list = AppDatabase.getInstance(CustomApplication.ctx!!)?.userDao()?.getAllUser()
            Log.d("list", "$list")
            //TODO : uid로 현재 사용자 찾기
            list?.forEach { user ->
                if (user.uid == userUid) currentUser = user
                Log.d("currentUser","$currentUser")
            }
        }.start()
    }

}