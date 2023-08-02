package com.unimind.unithing.Data

import com.google.firebase.Timestamp
import java.util.Date

/**사용자가 글을 업로드하면 등록되는 정보들*/
data class Comment(
    val user_nickname: String="", //uid가 아닌 닉네임
    val user_belong: String = "",
    val content: String = "",
    val date: Timestamp = Timestamp.now(),
    val like: Int = 0,
    val comment_id: String = "",
)
