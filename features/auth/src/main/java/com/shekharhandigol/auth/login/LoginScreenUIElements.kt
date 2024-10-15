package com.shekharhandigol.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shekharhandigol.theme.BothPreviews

@BothPreviews
@Composable
fun LoginScreenLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(10.dp)
                .padding(50.dp),
            strokeWidth = 4.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            trackColor = MaterialTheme.colorScheme.primaryContainer,
        )
    }
}