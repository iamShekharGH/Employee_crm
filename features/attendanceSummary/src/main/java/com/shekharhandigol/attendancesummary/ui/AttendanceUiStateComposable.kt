package com.shekharhandigol.attendancesummary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shekharhandigol.theme.BothPreviews

@BothPreviews
@Composable
fun NoDataView(goToHome: () -> Unit = {}) {
    val noDataTextStyle = TextStyle(
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { goToHome() },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                imageVector = Icons.Filled.Close,
                contentDescription = "No data found",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "No Data Found",
                style = noDataTextStyle,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

    }
}

@BothPreviews
@Composable
fun LoadingView(goToHome: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { goToHome() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(128.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}