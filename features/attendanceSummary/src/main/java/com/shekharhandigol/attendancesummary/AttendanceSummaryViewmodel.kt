package com.shekharhandigol.attendancesummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.data.HolidaysDAO
import com.shekharhandigol.data.models.EmployeeHolidays
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceSummaryViewmodel @Inject constructor(
    private val dao: HolidaysDAO
) : ViewModel() {

    private val _attendanceSummaryState =
        MutableStateFlow<AttendanceSummaryState>(AttendanceSummaryState.NoData)
    val attendanceSummaryState = _attendanceSummaryState.asStateFlow()

    init {
        insertNationalHolidays()
        getAll()
    }

    private fun insertNationalHolidays() {
        viewModelScope.launch {
            dao.deleteAllHolidays()
            dao.insertNationalHolidays()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            _attendanceSummaryState.value = AttendanceSummaryState.Loading
            dao.getAll().collectLatest { list ->
                _attendanceSummaryState.value = if (list.isEmpty()) {
                    AttendanceSummaryState.NoData
                } else
                    AttendanceSummaryState.Data(list)
            }
        }
    }
}

sealed class AttendanceSummaryState {
    data object NoData : AttendanceSummaryState()
    data object Loading : AttendanceSummaryState()
    data class Data(val data: List<EmployeeHolidays>) : AttendanceSummaryState()

}