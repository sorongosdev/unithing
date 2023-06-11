package com.unimind.unithing.Data

import java.util.Date

/**사용자가 글을 업로드하면 등록되는 정보들*/
data class Post(
    val userName: String, //uid가 아닌 닉네임
    val title: String,
    val content: String,
    val date: Date,
    val like: Int,
    val view: Int,
    val comment: Int, //메인에서는 댓글
    val hotCommentId: String, //제일 좋아요를 많이 받은 댓글이 메인에 보여짐
    val history: List<String>, //이전 수정 내용
)
