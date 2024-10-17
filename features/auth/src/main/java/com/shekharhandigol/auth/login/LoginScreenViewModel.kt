package com.shekharhandigol.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.auth.LoginUserUiState
import com.shekharhandigol.domain.CheckUserStateUseCase
import com.shekharhandigol.domain.LoginUseCase
import com.shekharhandigol.domain.ValidationUseCase
import com.shekharhandigol.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validationUseCase: ValidationUseCase,
    private val checkUserStateUseCase: CheckUserStateUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginUserUiState>(LoginUserUiState.FirstBoot)
    val loginStateFlow = _loginStateFlow.asStateFlow()


    private val _passwordIsWrong = MutableStateFlow(Pair(false, ""))
    val passwordIsWrong = _passwordIsWrong.asStateFlow()

    private val _showLoading = MutableStateFlow(false)
    val showLoading = _showLoading.asStateFlow()


    val loginToAccount: (String, String) -> Pair<Boolean, Boolean> = { username, password ->
        val validationResult = validateText(username, password)

        if (!validationResult.first && !validationResult.second) {
            makeLoginRequestAndSaveInfo(username, password)
        }

        validationResult
    }

    private fun makeLoginRequestAndSaveInfo(username: String, password: String) {
        viewModelScope.launch {
            showLoadingUI()
            _loginStateFlow.value = when (val res = loginUseCase.invoke(username, password)) {
                is Resource.Error -> {
                    hideLoadingUI()
                    _passwordIsWrong.value = Pair(true, res.message)
                    LoginUserUiState.Response.Error(res.message)
                }

                is Resource.Loading -> {
                    showLoadingUI()
                    LoginUserUiState.Response.Loading
                }

                is Resource.Success -> {
                    hideLoadingUI()
                    LoginUserUiState.Response.Success(res.data.data)
                }
            }
        }
    }

    private fun showLoadingUI() {
        viewModelScope.launch {
            _showLoading.value = true
        }
    }

    private fun hideLoadingUI() {
        viewModelScope.launch {
            _showLoading.value = false
        }
    }


    fun checkUserSignInState() {
        viewModelScope.launch {
            _loginStateFlow.value = checkUserStateUseCase.invoke()
            /*dataStore.getUserInformation().collectLatest { userInfo ->
                _loginStateFlow.value = determineLoginState(userInfo)
                Log.d("LoginScreenViewModel", "checkUserSignInState: $userInfo")
            }*/
        }
    }

    /*private fun determineLoginState(userInfo: UserInformation): LoginUserUiState.UserState {
        return when {
            userInfo.isValid() -> LoginUserUiState.UserState.UserIsLoggedIn
            userInfo.isPartiallyValid() -> LoginUserUiState.UserState.UserIsLoggedOut
            else -> LoginUserUiState.UserState.UserIsNew
        }
    }*/

    val validateUsername: (String) -> Boolean = { username ->
        validationUseCase.checkUsername(username)
    }
    val validatePassword: (String) -> Boolean = { password ->
        validationUseCase.checkPassword(password)
    }

    private fun validateText(username: String, password: String): Pair<Boolean, Boolean> {
        return validationUseCase.invoke(username, password)
    }

}