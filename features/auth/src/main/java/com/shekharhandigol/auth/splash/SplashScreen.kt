package com.shekharhandigol.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shekharhandigol.auth.R
import com.shekharhandigol.theme.BothPreviews

@Composable
fun SplashScreen(navigateToLogin: () -> Unit) {
    Splash(navigateToLogin)
}

@Composable
fun Splash(navigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = "logo")
        Text(
            text = stringResource(R.string.employee_crm_app),
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(),
            text = stringResource(R.string.welcome_to_employee),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp

        )

        ElevatedButton(
            onClick = { navigateToLogin() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.get_started),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp
            )
        }
    }

}

@BothPreviews
@Composable
fun PreviewSplash() {
    Splash({})
}