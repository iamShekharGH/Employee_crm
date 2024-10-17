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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.SalaryBreakdown
import com.shekharhandigol.theme.BothPreviews

@Composable
fun SalarySummaryScreen(
    goToHome: () -> Unit,
    viewModel: SalarySummaryScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.salarySummaryStateFlow.collectAsStateWithLifecycle()
    when (val ui = uiState.value) {
        SalaryScreenUIState.FirstBoot -> {
            FirstBoot(goToHome)
        }

        SalaryScreenUIState.NoInformation -> {
            NoInformation(goToHome)
        }

        is SalaryScreenUIState.SalarySummary -> {
            SalarySummaryUI(ui.salaryBreakdown, goToHome)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalarySummaryUI(salaryBreakdown: SalaryBreakdown, goToHome: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        topBar = {
            TopAppBar(title = { Text(text = "Salary Summary") },
                navigationIcon = {
                    IconButton(onClick = { goToHome() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back"
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
                    .padding(top = 8.dp)
                    .fillMaxWidth(),

                ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    RowInformation(
                        heading = "Net Salary :",
                        body = "₹${salaryBreakdown.netSalary}",
                        larger = true
                    )
                    RowInformation(
                        heading = "Earnings - Deductions",
                        body = "₹${salaryBreakdown.totalEarnings}-₹${salaryBreakdown.totalDeductions}"
                    )
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
                    RowInformation("Monthly Salary", "₹ ${salaryBreakdown.basicSalary}")
                    RowInformation("HRA (House Rent Allowance)", "₹ ${salaryBreakdown.hra}")
                    RowInformation(
                        "Conveyance Allowance",
                        "₹ ${salaryBreakdown.conveyanceAllowance}"
                    )
                    RowInformation("Medical Allowance", "₹ ${salaryBreakdown.medicalAllowance}")
                    RowInformation("Other Allowances", "₹ ${salaryBreakdown.otherAllowances}")
                    HorizontalDivider()
                    RowInformation("Total Earnings", "₹ ${salaryBreakdown.totalEarnings}")

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

                    RowInformation("Income Tax", "₹ ${salaryBreakdown.incomeTax}")
                    RowInformation("Provident Fund (PF)", "₹ ${salaryBreakdown.providentFund}")
                    RowInformation("Professional Tax", "₹ ${salaryBreakdown.professionalTax}")
                    RowInformation("Other Deductions", "₹ ${salaryBreakdown.otherDeductions}")
                    HorizontalDivider()
                    RowInformation("Total Deductions", "₹ ${salaryBreakdown.totalDeductions}")

                }
            }
        }
    }
}

@BothPreviews
@Composable
fun PreviewSalarySummaryUI() {
    SalarySummaryUI(
        SalaryBreakdown(
            basicSalary = 50000.0,
            hra = 15000.0,
            conveyanceAllowance = 1000.0,
            medicalAllowance = 500.0,
            otherAllowances = 0.0,
            totalEarnings = 0.0,
            incomeTax = 0.0,
            providentFund = 0.0,
            professionalTax = 250.0,
            otherDeductions = 0.0,
            totalDeductions = 0.0,
            netSalary = 0.0
        ),
        goToHome = {}
    )
}
