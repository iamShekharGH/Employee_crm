package com.shekharhandigol.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.auth.firebaseLogin.SignInResult
import com.shekharhandigol.auth.firebaseLogin.SignInState
import com.shekharhandigol.auth.login.validation.ValidatorFactory
import com.shekharhandigol.data.models.EmployeeGender
import com.shekharhandigol.data.models.UserInformation
import com.shekharhandigol.datastore.SessionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validatorFactory: ValidatorFactory,
    private val dataStore: SessionHandler
) : ViewModel() {


    private val _loginStateFlow = MutableStateFlow(SignInState())
    val loginStateFlow = _loginStateFlow.asStateFlow()

    fun signInResult(result: SignInResult) {
        _loginStateFlow.update {
            it.copy(
                isSignedIn = result.data != null,
                errorMessage = result.errorMsg
            )
        }
    }

    fun resetUser() {
        _loginStateFlow.update { SignInState() }
    }

    val onSignInClick: () -> Unit = {
        resetUser()
    }


    val loginToAccount: (String, String) -> Pair<Boolean, Boolean> = { username, password ->
        val validationResult = validateText(username, password)

        if (validationResult.first && validationResult.second) {
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
                    photoUrl = ""
                )

            )
        }

        validationResult

    }

    private fun saveUserInformation(appUserInformation: UserInformation) {
        viewModelScope.launch {
            dataStore.saveSession(appUserInformation)
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

    fun loginToAccount(s: String, s1: String) {

    }

}