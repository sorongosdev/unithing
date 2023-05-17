package com.unimind.unithing.Repository.SignUserRemoteDataSource

class SignUserRepositoryImpl : SingUserRepository{
    override fun requestSignUp(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}