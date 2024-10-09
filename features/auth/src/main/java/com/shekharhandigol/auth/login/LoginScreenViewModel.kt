package com.shekharhandigol.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.auth.LoginUserUiState
import com.shekharhandigol.auth.login.validation.ValidatorFactory
import com.shekharhandigol.data.models.EmployeeGender
import com.shekharhandigol.data.models.UserInformation
import com.shekharhandigol.data.models.isPartiallyValid
import com.shekharhandigol.data.models.isValid
import com.shekharhandigol.datastore.SessionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validatorFactory: ValidatorFactory,
    private val dataStore: SessionHandler
) : ViewModel() {


    private val _loginStateFlow = MutableStateFlow<LoginUserUiState>(LoginUserUiState.FirstBoot)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    init {
        checkUserSignInState()
    }

    private fun resetApp(){
        viewModelScope.launch {
            dataStore.clear()
        }
    }

    val loginToAccount: (String, String) -> Pair<Boolean, Boolean> = { username, password ->
        val validationResult = validateText(username, password)

        if (!validationResult.first && !validationResult.second) {
            makeLoginRequestAndSaveInfo()
        }

        validationResult
    }

    private fun makeLoginRequestAndSaveInfo() {

        saveUserInformation(
            UserInformation(
                eid = 1,
                name = "Shekhar Handigol",
                title = "Software Engineer",
                age = 25,
                birthday = "1997-01-01",
                presentToday = true,
                salaryCredited = false,
                email = "joseph.mckenna@examplepetstore.com",
                employeeGender = EmployeeGender.Male,
                photoUrl = "",
                salary = 2100000
            )
        )
    }

    private fun saveUserInformation(appUserInformation: UserInformation) {
        viewModelScope.launch {
            dataStore.saveSession(appUserInformation)
        }
    }

    private fun checkUserSignInState() {
        viewModelScope.launch {
            val userInformation = dataStore.getUserInformation()
            userInformation.collectLatest { info ->

                _loginStateFlow.value = when {
                    info.isValid() -> LoginUserUiState.UserIsLoggedIn
                    info.isPartiallyValid() -> LoginUserUiState.UserIsLoggedOut
                    else -> LoginUserUiState.UserIsNew
                }
                Log.d("LoginScreenViewModel", "checkUserSignInState: $info")
            }
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