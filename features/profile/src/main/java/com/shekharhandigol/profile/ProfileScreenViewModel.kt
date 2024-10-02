package com.shekharhandigol.profile

import androidx.lifecycle.ViewModel
import com.shekharhandigol.domain.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val repo: EmployeeRepository
) : ViewModel() {
}