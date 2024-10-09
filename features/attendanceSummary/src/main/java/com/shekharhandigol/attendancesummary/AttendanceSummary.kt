@file:OptIn(ExperimentalMaterial3Api::class)

package com.shekharhandigol.attendancesummary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.attendancesummary.ui.AttendanceDaysUI
import com.shekharhandigol.attendancesummary.ui.LoadingView
import com.shekharhandigol.attendancesummary.ui.NoDataView
import com.shekharhandigol.attendancesummary.ui.TopSummaryCard
import com.shekharhandigol.data.models.EmployeeHolidays
import com.shekharhandigol.theme.BothPreviews

@Composable
fun AttendanceSummaryScreen(
    goToHome: () -> Unit,
    viewmodel: AttendanceSummaryViewmodel = hiltViewModel()
) {
    val state = viewmodel.attendanceSummaryState.collectAsStateWithLifecycle()
    when (val ui = state.value) {
        is AttendanceSummaryState.Data -> {
            AttendanceSummaryUI(ui.data, goToHome)
        }

        AttendanceSummaryState.Loading -> {
            LoadingView(goToHome)
        }

        AttendanceSummaryState.NoData -> {
            NoDataView(goToHome)
        }
    }
}


@Composable
fun AttendanceSummaryUI(employeeHolidays: List<EmployeeHolidays>, goToHome: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        topBar = {
            TopAppBar(title = { Text(text = "Attendance Summary") },
                navigationIcon = {
                    IconButton(onClick = { goToHome() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back Home"
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
            val (leavesTaken, leavesLeft) = calculateLeaves(employeeHolidays)

            TopSummaryCard(leavesTaken, leavesLeft)

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
                        items(employeeHolidays.sortedBy { it.holidayDate }) { holiday ->
                            AttendanceDaysUI(holiday)
                        }
                    }
                }


            }
        }
    }
}

private fun calculateLeaves(employeeHolidays: List<EmployeeHolidays>): Pair<Int, Int> {
    val leavesTaken = employeeHolidays.count { it.isHolidayTaken }
    val leavesLeft = employeeHolidays.count { !it.isHolidayTaken }
    return leavesTaken to leavesLeft
}

@BothPreviews
@Composable
fun PreviewAttendanceSummaryUI() {
    AttendanceSummaryUI(listOf(), { })
}