package com.shekharhandigol.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.auth.LoginUserUiState
import com.shekharhandigol.auth.login.validation.ValidatorFactory
import com.shekharhandigol.data.LoginRepository
import com.shekharhandigol.datastore.SessionHandler
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.Resource
import com.shekharhandigol.models.UserInformation
import com.shekharhandigol.models.isPartiallyValid
import com.shekharhandigol.models.isValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validatorFactory: ValidatorFactory,
    private val dataStore: SessionHandler,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginUserUiState>(LoginUserUiState.FirstBoot)
    val loginStateFlow = _loginStateFlow.asStateFlow()


    private val _passwordIsWrong = MutableStateFlow(Pair(false, ""))
    val passwordIsWrong = _passwordIsWrong.asStateFlow()

    private val _showLoading = MutableStateFlow(false)
    val showLoading = _showLoading.asStateFlow()


    /*init {
        checkUserSignInState()
    }*/

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
            val res = loginRepository.loginUser(
                LoginRequest(
                    username = username,
                    password = password
                )
            )

            _loginStateFlow.value = when (res) {
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
                    saveUserInformation(res.data.data)
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

    private fun saveUserInformation(appUserInformation: UserInformation) {
        viewModelScope.launch {
            dataStore.saveSession(appUserInformation)
        }
    }

    fun checkUserSignInState() {
        viewModelScope.launch {
            dataStore.getUserInformation().collectLatest { userInfo ->
                _loginStateFlow.value = determineLoginState(userInfo)
                Log.d("LoginScreenViewModel", "checkUserSignInState: $userInfo")
            }
        }
    }

    private fun determineLoginState(userInfo: UserInformation): LoginUserUiState.UserState {
        return when {
            userInfo.isValid() -> LoginUserUiState.UserState.UserIsLoggedIn
            userInfo.isPartiallyValid() -> LoginUserUiState.UserState.UserIsLoggedOut
            else -> LoginUserUiState.UserState.UserIsNew
        }
    }

    val validateUsername: (String) -> Boolean = { username ->
        !validatorFactory.getValidator(ValidatorFactory.ValidationTypes.EMAIL).check(username)
    }
    val validatePassword: (String) -> Boolean = { password ->
        !validatorFactory.getValidator(ValidatorFactory.ValidationTypes.PASSWORD).check(password)
    }

    private fun validateText(username: String, password: String): Pair<Boolean, Boolean> {
        return Pair(validateUsername(username), validatePassword(password))
    }

}