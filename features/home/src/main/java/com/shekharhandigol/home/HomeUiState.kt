package com.shekharhandigol.home

import com.shekharhandigol.data.models.Employee

internal sealed class HomeUiState {

    data object Starting : HomeUiState()
    data object Empty : HomeUiState()
    data object Error : HomeUiState()
    data object Loading : HomeUiState()
    data class EmployeeList(val list: List<Employee>) : HomeUiState()


}