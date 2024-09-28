package com.shekharhandigol.salarysummary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shekharhandigol.theme.BothPreviews

@Composable
fun SalarySummaryScreen() {
    SalarySummaryUI()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalarySummaryUI() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        topBar = {
            TopAppBar(title = { Text(text = "Salary Summary") },
                navigationIcon = {
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

            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),

                ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    RowInformation(heading = "Net Salary :", body = "₹48,133", larger = true)
                    RowInformation(heading = "Earnings - Deductions", body = "₹66,667-₹18,534")
                }
            }
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                        text = "Monthly Salary Breakdown:",
                        fontSize = 21.sp, textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                    RowInformation("Basic Salary", "₹ 40,000")
                    RowInformation("HRA (House Rent Allowance)", "₹ 15,000")
                    RowInformation("Conveyance Allowance", "₹ 5,000")
                    RowInformation("Medical Allowance", "₹ 3,000")
                    RowInformation("Other Allowances", "₹ 3,667")
                    HorizontalDivider()
                    RowInformation("Total Earnings", "₹ 66,667")

                }
            }
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                        text = "Deduction:",
                        fontSize = 21.sp, textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )

                    RowInformation("Income Tax", "₹ 10,000")
                    RowInformation("Provident Fund (PF)", "₹ 6,667")
                    RowInformation("Professional Tax", "₹ 200")
                    RowInformation("Other Deductions", "₹ 1,000")
                    HorizontalDivider()
                    RowInformation("Total Deductions", "₹ 18,534")

                }
            }
        }
    }
}

@BothPreviews
@Composable
fun PreviewSalarySummaryUI() {
    SalarySummaryUI()
}
