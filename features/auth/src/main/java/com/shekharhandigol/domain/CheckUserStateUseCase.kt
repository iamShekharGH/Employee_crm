package com.shekharhandigol.domain

import com.shekharhandigol.auth.LoginUserUiState
import com.shekharhandigol.datastore.SessionHandler
import com.shekharhandigol.models.UserInformation
import com.shekharhandigol.models.isPartiallyValid
import com.shekharhandigol.models.isValid
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class CheckUserStateUseCase @Inject constructor(
    private val dataStore: SessionHandler

) {

    suspend fun invoke(): LoginUserUiState.UserState {
        val userInfo = dataStore.getUserInformation().first()

        Timber.tag("LoginScreenViewModel").d("checkUserSignInState: $userInfo")
        return determineLoginState(userInfo)
    }

    private fun determineLoginState(userInfo: UserInformation): LoginUserUiState.UserState {
        return when {
            userInfo.isValid() -> LoginUserUiState.UserState.UserIsLoggedIn
            userInfo.isPartiallyValid() -> LoginUserUiState.UserState.UserIsLoggedOut
            else -> LoginUserUiState.UserState.UserIsNew
        }
    }

}