package com.unimind.unithing.Repository.LocalDataSource

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unimind.unithing.AppDatabase
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Data.User

/**댓글버튼이 클릭 됐을때, postInfo를 저장하는 싱글톤 객체, 뷰 재사용을 위해 필요*/
object PostInfoRepositoryImpl{
    var postInfo: Post? = null
}