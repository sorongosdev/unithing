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
    var currentUser: User? = null

    /**로그인/회원가입될 때 회원정보를 업데이트, 이후 닉네임 변경, 과 변경 등등 변경 관련 함수 생기면 리네이밍 필요*/
    override fun insertUserInfo(user: User){
        Log.d("currentUser","$user")
        Thread {
            //로컬에 회원정보가 없다면 로그인시 추가해준다
            try{
                AppDatabase.getInstance(CustomApplication.ctx!!)?.userDao()?.insert(user)
            }
            //로컬에 회원정보가 있으면 정보를 다시 받아와 업데이트 해줌
            catch(e: Exception){
                AppDatabase.getInstance(CustomApplication.ctx!!)?.userDao()?.update(user)
            }
        }.start()
    }
}