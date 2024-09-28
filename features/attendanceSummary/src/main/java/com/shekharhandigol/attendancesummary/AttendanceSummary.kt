@file:OptIn(ExperimentalMaterial3Api::class)

package com.shekharhandigol.attendancesummary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shekharhandigol.theme.BothPreviews

@Composable
fun AttendanceSummaryScreen() {
    AttendanceSummaryUI()
}


@Composable
fun AttendanceSummaryUI() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        topBar = {
            TopAppBar(title = { Text(text = "Attendance Summary") }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Leaves left in the year.")
            }

        }

    }

}

@BothPreviews
@Composable
fun PreviewAttendanceSummaryUI() {

}