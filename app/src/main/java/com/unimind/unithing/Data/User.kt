package com.unimind.unithing.Data

import android.provider.ContactsContract.CommonDataKinds.Email

data class User (
    val email: String? = null,
    val nickname: String? = null,
    val major: String? = null,
    val level: Int? = null, // 레벨 (누적 경험치)
    val experience: Int? = null, // 주기적으로 초기화되는 경험치
    val authorized: Boolean? = null,
    val badge: String? = null, // 향후 컬렉션 형식으로 변경해야 함 // 브실골
    val myComment: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myPost: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myWishPost: String? = null // 향후 컬렉션 형식으로 변경해야 함
)