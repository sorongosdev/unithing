package com.unimind.unithing.Data

import com.google.firebase.Timestamp
import java.util.Date

/**사용자가 글을 업로드하면 등록되는 정보들*/
data class Post(
    val nickname: String = "", //uid가 아닌 닉네임
    val belong: String = "",
    val title: String = "",
    val content: String = "",
    val date: Timestamp = Timestamp.now(),
    val like: Int = 0,
    val view: Int = 0,
    val comment_cnt: Int = 0, //메인에서는 댓글
    val history: List<String>? = emptyList(), //이전 수정 내용
    val postId: String = "",
    val type: Int = 0,
)
