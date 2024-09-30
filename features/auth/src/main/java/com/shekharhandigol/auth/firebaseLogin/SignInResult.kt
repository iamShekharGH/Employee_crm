package com.shekharhandigol.auth.firebaseLogin

data class SignInResult(
    val data: UserData?,
    val errorMsg: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePicUrl: String?
)