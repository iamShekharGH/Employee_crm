package com.shekharhandigol.auth

sealed class LoginUserUiState {
    data object UserIsLoggedIn : LoginUserUiState()
    data object UserIsLoggedOut : LoginUserUiState()
    data object UserIsNew : LoginUserUiState()
    data object FirstBoot : LoginUserUiState()
}