package com.unimind.unithing.Data

import com.google.firebase.Timestamp
import java.util.Date

/**사용자가 글을 업로드하면 등록되는 정보들*/
data class Post(
    val userName: String, //uid가 아닌 닉네임
    val title: String,
    val content: String,
    val date: Timestamp,
    val like: Int=0,
    val view: Int=0,
    val comment: Int=0, //메인에서는 댓글
    val history: List<String>? , //이전 수정 내용
)
