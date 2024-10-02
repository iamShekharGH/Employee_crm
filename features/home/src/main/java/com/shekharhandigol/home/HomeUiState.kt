package com.shekharhandigol.home

import com.shekharhandigol.data.models.Employee

sealed class HomeUiState {

    data object Starting : HomeUiState()
    data object Empty : HomeUiState()
    data object Error : HomeUiState()
    data class EmployeeList(val list: List<Employee>) : HomeUiState()


}