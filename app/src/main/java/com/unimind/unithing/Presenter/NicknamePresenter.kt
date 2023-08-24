package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.NicknameContract
import com.unimind.unithing.Repository.RemoteDataSource.AuthorityRepositoryImpl

/**추후 UserInfoPresenter와 통합후 제거 예정*/
class NicknamePresenter() : NicknameContract.Presenter {
    /**authorized 값을 DB로부터 받아와, 뷰를 보여줄지 숨길지 결정한다.*/
    override fun makeRandomId(): String {
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(20) { charset.random() }
            .joinToString("")
    }
}