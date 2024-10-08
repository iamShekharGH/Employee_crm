package com.shekharhandigol.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.data.models.isValid
import com.shekharhandigol.datastore.SessionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val dataStore: SessionHandler
) : ViewModel() {


    private val _profileUIState = MutableStateFlow<ProfileUiState>(ProfileUiState.NoInfoAvailable)
    val profileUIState = _profileUIState.asStateFlow()

    init {
        getProfileInfo()
    }

    private fun getProfileInfo() {
        viewModelScope.launch {
            dataStore.getUserInformation().collectLatest { info ->
                if (info.isValid()) {
                    _profileUIState.value = ProfileUiState.ThisIsProfileInfo(info)
                } else {
                    _profileUIState.value = ProfileUiState.NoInfoAvailable
                }

            }

        }
    }


}

