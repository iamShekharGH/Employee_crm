package com.shekharhandigol.profile

import com.shekharhandigol.models.UserInformation

sealed class ProfileUiState {
    data object NoInfoAvailable : ProfileUiState()
    data class ThisIsProfileInfo(val userInformation: UserInformation) : ProfileUiState()
}