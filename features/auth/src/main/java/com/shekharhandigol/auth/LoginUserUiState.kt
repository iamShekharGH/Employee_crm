package com.shekharhandigol.auth

import com.shekharhandigol.models.UserInformation

sealed class LoginUserUiState {
    data object FirstBoot : LoginUserUiState()

    sealed class Response : LoginUserUiState() {
        data object Error : Response()
        data class Success(val userInformation: UserInformation) : Response()
        data object Loading : Response()
    }

    sealed class UserState : LoginUserUiState() {
        data object UserIsLoggedIn : UserState()
        data object UserIsLoggedOut : UserState()
        data object UserIsNew : UserState()
    }
}