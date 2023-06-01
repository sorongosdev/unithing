package com.unimind.unithing.Repository.UserRemoteDataSource



interface UserRepository {
    fun createCertificateDB(callback : (Boolean) -> Unit)
}