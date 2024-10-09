package com.shekharhandigol.salarysummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.SalaryBreakdown
import com.shekharhandigol.calculateSalaryBreakdown
import com.shekharhandigol.datastore.SessionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalarySummaryScreenViewModel @Inject constructor(
    private val dataStore: SessionHandler
) : ViewModel() {

    private val _salarySummaryStateFlow =
        MutableStateFlow<SalaryScreenUIState>(SalaryScreenUIState.FirstBoot)
    val salarySummaryStateFlow = _salarySummaryStateFlow.asStateFlow()

    init {
        getSalaryInformation()
    }


    private fun getSalaryInformation() {
        viewModelScope.launch {
            dataStore.getUserInformation().collectLatest { info ->

                _salarySummaryStateFlow.value = if (info.salary <= 0) {
                    SalaryScreenUIState.SalarySummary(calculateSalaryBreakdown(2100000.toDouble()))
//                    SalaryScreenUIState.NoInformation
                } else {
                    SalaryScreenUIState.SalarySummary(calculateSalaryBreakdown(info.salary.toDouble()))
                }
            }
        }
    }
}

sealed class SalaryScreenUIState {
    data object FirstBoot : SalaryScreenUIState()
    data object NoInformation : SalaryScreenUIState()
    data class SalarySummary(val salaryBreakdown: SalaryBreakdown) : SalaryScreenUIState()

}