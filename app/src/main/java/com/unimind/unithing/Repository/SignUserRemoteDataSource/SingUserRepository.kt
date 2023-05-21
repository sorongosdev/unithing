package com.unimind.unithing.Repository.SignUserRemoteDataSource

interface SingUserRepository {
    fun requestSignUp(email: String, password: String, callback: (Boolean, String?) -> Unit)
    fun requestSignIn(email: String, password: String, callback: (Boolean, String?) -> Unit)
}