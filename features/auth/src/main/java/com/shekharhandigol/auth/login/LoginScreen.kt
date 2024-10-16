package com.shekharhandigol.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.auth.LoginUserUiState
import com.shekharhandigol.auth.R
import com.shekharhandigol.theme.BothPreviews

@Composable
fun LoginScreen(
    onSignInClick: () -> Unit,
    goToHome: () -> Unit,
    viewModel: LoginScreenViewModel
) {
    val state = viewModel.loginStateFlow.collectAsStateWithLifecycle()
    val passwordErrorState = viewModel.passwordIsWrong.collectAsStateWithLifecycle()
    val showLoadingState = viewModel.showLoading.collectAsStateWithLifecycle()

    val context = LocalContext.current
    /*LaunchedEffect(key1 = state.value) {
        Toast.makeText(
            context,
            state.value.toString(),
            Toast.LENGTH_LONG
        ).show()
    }*/

    when (state.value) {

        LoginUserUiState.UserState.UserIsLoggedIn,
        is LoginUserUiState.Response.Success -> {
            goToHome()
        }

        is LoginUserUiState.Response.Error,
        LoginUserUiState.Response.Loading,
        LoginUserUiState.UserState.UserIsLoggedOut,
        LoginUserUiState.UserState.UserIsNew -> {
            LoginUI(
                login = viewModel.loginToAccount,
                validateUsername = viewModel.validateUsername,
                validatePassword = viewModel.validatePassword,
                wrongPasswordState = passwordErrorState,
                onSignInClick = onSignInClick,
                showLoading = showLoadingState
            )
        }

        else -> {
            Toast.makeText(
                context,
                state.value.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun LoginUI(
    login: (String, String) -> Pair<Boolean, Boolean>,
    validateUsername: (String) -> Boolean,
    validatePassword: (String) -> Boolean,
    wrongPasswordState: State<Pair<Boolean, String>>,
    onSignInClick: () -> Unit,
    showLoading: State<Boolean>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .verticalScroll(state = rememberScrollState()),
    ) {

        var username by remember { mutableStateOf("anita.sharma") }
        var usernameError by remember { mutableStateOf(false) }


        var password by remember { mutableStateOf("password123") }
        var passwordError by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo), contentDescription = "logo",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                isError = usernameError,
                supportingText = {
                    if (usernameError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Cannot be empty.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (usernameError)
                        Icon(
                            Icons.Filled.Warning, "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                onValueChange = {
                    username = it
                    usernameError = validateUsername(username)
                },
                label = { Text(text = stringResource(R.string.username)) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                isError = passwordError,
                supportingText = {
                    if (passwordError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Cannot be empty.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (passwordError)
                        Icon(
                            Icons.Filled.Warning, "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                onValueChange = {
                    password = it
                    passwordError = validatePassword(password)
                },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = stringResource(R.string.password)) }
            )
            if (wrongPasswordState.value.first)
                Text(
                    wrongPasswordState.value.second,
                    color = MaterialTheme.colorScheme.error
                )

            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    val validationResult = login(username, password)
                    usernameError = validationResult.first
                    passwordError = validationResult.second
                }) {
                Text(text = stringResource(R.string.login))
            }

            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    onSignInClick()
//                    goToHome()
                }) {
                Text(text = "Login With Google!(Go Home)")
            }

            if (showLoading.value) {
                LoginScreenLoadingView()
            }
        }
    }
}

@Composable
@BothPreviews
fun PreviewLoginUI() {
    LoginUI(
        login = { _, _ ->
        Pair(false, false)
        },
        validateUsername = { false },
        validatePassword = { false },
        wrongPasswordState = remember { mutableStateOf(Pair(false, "")) },
        onSignInClick = {},
        showLoading = remember { mutableStateOf(false) })
}