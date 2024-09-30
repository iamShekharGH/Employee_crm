package com.shekharhandigol.auth.firebaseLogin

data class SignInState(
    val isSignedIn: Boolean = false,
    val errorMessage: String? = null
)
