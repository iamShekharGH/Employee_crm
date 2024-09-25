package com.shekharhandigol.auth.login

import androidx.lifecycle.ViewModel
import com.shekharhandigol.auth.login.validation.ValidatorFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validatorFactory: ValidatorFactory
) : ViewModel() {


    val loginToAccount: (String, String) -> Pair<Boolean, Boolean> = { username, password ->
        validateText(username, password)
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