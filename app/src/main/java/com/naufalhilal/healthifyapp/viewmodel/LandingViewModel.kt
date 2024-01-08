package com.naufalhilal.healthifyapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.model.UserModel
import com.naufalhilal.healthifyapp.data.repository.HealthifyRepo
import com.naufalhilal.healthifyapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(private val repository: HealthifyRepo) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserModel>>
        get() = _uiState

    fun getSession() {
        viewModelScope.launch {
            repository.getSession()
                .collect { session ->
                    Timber.tag("sessionViewmodel").e(session.toString())
                    _uiState.value = UiState.Success(session)
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}
