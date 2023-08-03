package com.unimind.unithing.Data

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unimind.unithing.Presenter.NicknamePresenter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = false) val uid: String = "",
    val email: String? = null,
    val nickname: String? = "@user-${NicknamePresenter().makeRandomId()}",
    /**major: db상 구분, belong: 인증 후 소속 업데이트*/
    val major: String? = null,
    val belong: String? = null,
    val type: String? = null,
    val level: Int? = null, // 레벨 (누적 경험치)
    val experience: Int? = null, // 주기적으로 초기화되는 경험치
    val authorized: Boolean? = null,
    val badge: String? = null, // 향후 컬렉션 형식으로 변경해야 함 // 브실골
    val myComment: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myPost: String? = null, // 향후 컬렉션 형식으로 변경해야 함
    val myWishPost: String? = null // 향후 컬렉션 형식으로 변경해야 함
): Parcelable