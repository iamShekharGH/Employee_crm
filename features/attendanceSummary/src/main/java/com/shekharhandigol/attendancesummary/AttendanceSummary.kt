@file:OptIn(ExperimentalMaterial3Api::class)

package com.shekharhandigol.attendancesummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            8.dp
                        ),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.onErrorContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.errorContainer
                    )

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Icon",
                            tint = MaterialTheme.colorScheme.errorContainer
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "07", fontSize = 21.sp, textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Leaves Taken",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }


                }

                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            8.dp
                        ),
                    colors = CardColors(
//                        containerColor = MaterialTheme.colorScheme.tertiary,
                        containerColor = Color(0xFF008000),
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentColor = Color.White
                    )

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Icon",
                            tint = MaterialTheme.colorScheme.errorContainer
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "13", fontSize = 21.sp, textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Leaves Left",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }


                }
            }

            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Leaves Information.",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    LazyColumn {
                        item {
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                            AttendanceDaysUI()
                        }
                    }
                }


            }
        }
    }
}

@BothPreviews
@Composable
fun PreviewAttendanceSummaryUI() {
    AttendanceSummaryUI()
}