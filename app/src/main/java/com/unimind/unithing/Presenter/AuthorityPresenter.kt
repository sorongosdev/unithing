package com.unimind.unithing.Presenter

import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Repository.RemoteDataSource.AuthorityRepositoryImpl

class AuthorityPresenter(val view: AuthorityContract.View) : AuthorityContract.Presenter{
    /**authorized 값을 DB로부터 받아와, 뷰를 보여줄지 숨길지 결정한다.*/
    override fun showAuthority() {
        AuthorityRepositoryImpl.getAuthority { authorized ->
//            view.showToast("authorized : $authorized")
            if(authorized) view.isAuthorized() else view.notAuthorized()
        }
    }

}