package com.shekharhandigol.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.data.models.Gender
import com.shekharhandigol.theme.BothPreviews
import com.shekharhandigol.ui.EmployeeCard

@Composable
fun EmployeeListScreen(innerPadding: PaddingValues, list: List<Employee>) {
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {

            items(list.size) { index ->
                EmployeeCard(list[index])
            }
        }
    }
}


@BothPreviews
@Composable
fun PreviewEmployeeListScreen() {
    EmployeeListScreen(
        PaddingValues(), listOf(
            Employee(
                eid = 1234,
                name = "Rakesh Jhun Jhun",
                title = "Serial Jhadu Poocha",
                presentToday = true,
                salaryCredited = false,
                gender = Gender.MALE,
                photoUrl = ""
            )
        )
    )
}

