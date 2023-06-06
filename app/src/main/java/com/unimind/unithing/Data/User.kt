package com.unimind.unithing.Data

import android.provider.ContactsContract.CommonDataKinds.Email

data class User (
    val uid: String? = null,
    val email: String? = null,
    val nickname: String? = null,
    val major: String? = null,
    val type: String? = null,
    val level: Int? = null,
    val experience: Int? = null,
    val authorized: Boolean? = null,
    val badge: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myComment: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myPost: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myWishPost: String? = null // 향후 컬렉션 형식으로 변경해야 함
)