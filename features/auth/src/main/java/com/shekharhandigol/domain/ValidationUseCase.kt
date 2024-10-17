package com.shekharhandigol.domain

import com.shekharhandigol.auth.login.validation.ValidatorFactory
import javax.inject.Inject

class ValidationUseCase @Inject constructor(
    private val validatorFactory: ValidatorFactory
) {
    fun invoke(username: String, password: String): Pair<Boolean, Boolean> {

        return Pair(checkUsername(username), checkPassword(password))

    }

    fun checkUsername(username: String): Boolean {
        val usernameValidator =
            validatorFactory.getValidator(ValidatorFactory.ValidationTypes.EMAIL)
        return !usernameValidator.check(username)
    }

    fun checkPassword(password: String): Boolean {
        val passwordValidator =
            validatorFactory.getValidator(ValidatorFactory.ValidationTypes.PASSWORD)
        return !passwordValidator.check(password)
    }
}