package com.unimind.unithing.Data

import com.google.firebase.Timestamp
import java.util.Date

/**사용자가 글을 업로드하면 등록되는 정보들*/
data class Comment(
    val userName: String, //uid가 아닌 닉네임
    val content: String,
    val date: Timestamp,
    val like: Int=0,
)
