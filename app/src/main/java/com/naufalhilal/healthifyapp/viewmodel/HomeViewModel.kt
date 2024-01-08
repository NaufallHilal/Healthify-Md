package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.local.PreferencesDataSource
import com.naufalhilal.healthifyapp.data.local.dao.HealthDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val healthDataDao: HealthDataDao
) : ViewModel() {

    private val _exerciseCaloriesState = MutableStateFlow<Int?>(null)
    val exerciseCaloriesState: StateFlow<Int?> get() = _exerciseCaloriesState

    private val _selectedDateState = MutableStateFlow<String?>(null)
    val selectedDateState: StateFlow<String?> get() = _selectedDateState

    private val _weightsFlow = MutableStateFlow<List<Float>>(emptyList())
    val weightsFlow: StateFlow<List<Float>> get() = _weightsFlow

    private val _totalCaloriesFood = MutableStateFlow(0)
    val totalCaloriesFood: StateFlow<Int> get() = _totalCaloriesFood

    fun updateTotalCaloriesFood(calories: Int) {
        _totalCaloriesFood.value = calories
    }

    fun fetchWeights(day: Int) {
        viewModelScope.launch {
            preferencesDataSource.getWeight(day).collect { fetchedWeights ->
                _weightsFlow.value = fetchedWeights
            }
        }
    }

    fun saveWeight(weight: Float, day: Int) {
        viewModelScope.launch {
            preferencesDataSource.saveWeight(weight, day)
        }
    }

    fun saveSelectedDate(date: String) {
        viewModelScope.launch {
            preferencesDataSource.saveSelectedDate(date)
        }
    }

    fun getSelectedDate() {
        viewModelScope.launch {
            preferencesDataSource.getSelectedDate().collect { selectedDate ->
                _selectedDateState.value = selectedDate
            }
        }
    }

    fun saveExerciseCalories(exercise: Int) {
        viewModelScope.launch {
            preferencesDataSource.saveExerciseCalories(exercise)
            _exerciseCaloriesState.value = exercise
        }
    }

    fun getExerciseCalories() {
        viewModelScope.launch {
            preferencesDataSource.getExerciseCalories().collect { exercise ->
                _exerciseCaloriesState.value = exercise
            }
        }
    }
}
