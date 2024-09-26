package com.shekharhandigol.auth.login.validation

import javax.inject.Inject


interface Validate {
    fun check(text: String): Boolean
}

object ValidateEmail : Validate {
    /**
     * Must contain only letters, numbers, underscores, periods, and hyphens.
     * Must be between 3 and 20 characters long.
     */
    override fun check(text: String): Boolean {
        val usernameRegex = Regex("^[a-zA-Z0-9._-]{3,20}$")

        return text.isNotEmpty() && usernameRegex.matches(text)
    }
}


object ValidatePassword : Validate {
    /**
     * Contains at least one uppercase letter.
     * Contains at least one lowercase letter.
     * Contains at least one number.
     * Contains at least one special character (from the set @#$%^&+=).
     * Contains no whitespace.
     * Is at least 8 characters long.
     */
    override fun check(text: String): Boolean {

        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        return passwordRegex.matches(text)

    }
}

class ValidatorFactory @Inject constructor() {
    enum class ValidationTypes { EMAIL, PASSWORD }

    fun getValidator(validationTypes: ValidationTypes): Validate {
        return when(validationTypes){
            ValidationTypes.EMAIL -> ValidateEmail
            ValidationTypes.PASSWORD -> ValidatePassword
        }
    }
}