package com.shekharhandigol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.domain.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: EmployeeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Starting)
    val uiState = _uiState.asStateFlow()

    private lateinit var employeeList: Flow<List<Employee>>

    init {
        getList()
        insertDummyData()
        handle()
    }

    private fun insertDummyData() = viewModelScope.launch {
        repo.insertDummy()
        delay(2000L)
        getList()
    }

    private fun getList(){
        employeeList = repo.getAllEmployees()
    }

    private fun handle() = viewModelScope.launch {
        employeeList.collect { list ->
            _uiState.value = if (list.isEmpty()) {
                HomeUiState.Empty
            } else HomeUiState.EmployeeList(list)

        }
    }


    val _employeeList = repo.getAllEmployees()

}