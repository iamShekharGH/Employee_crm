package com.shekharhandigol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.domain.EmployeeRepository
import com.shekharhandigol.domain.HomeUseCase
import com.shekharhandigol.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Starting)
    val uiState = _uiState.asStateFlow()

    fun fetchEmployees() {
        viewModelScope.launch {
            _uiState.value = when (val res = homeUseCase.invoke()) {
                is Resource.Error -> {
                    HomeUiState.Error
                }

                is Resource.Loading -> {
                    HomeUiState.Loading
                }

                is Resource.Success -> {
                    HomeUiState.EmployeeList(res.data)
                }
            }
        }
    }
}