package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.data.local.PreferencesDataSource
import com.naufalhilal.healthifyapp.data.local.dao.HealthDataDao
import com.naufalhilal.healthifyapp.data.local.entity.HealthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
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

    fun deleteWeightEntry(healthData: HealthData) {
        viewModelScope.launch {
            healthDataDao.deleteHealthData(healthData)
            Timber.d("Successfully deleted weight entry with ID: ${healthData.health_data_id}")
        }
    }

    fun getAllWeightEntries(): List<HealthData> {
        return healthDataDao.getAllHealthData()
    }

    fun getWeightEntryById(id: Int): HealthData {
        return healthDataDao.getHealthDataById(id)
    }

    fun saveWeightEntry(weight: Float) {
        val currentHealthData = HealthData(
            calories = _exerciseCaloriesState.value?.toFloat() ?: 0f,
            weight = weight,
            height = 0f,
            steps = 0f,
            heart_rate = 0f
        )

        viewModelScope.launch {
            healthDataDao.insertHealthData(currentHealthData)
            Timber.d("Successfully inserted weight entry: $currentHealthData")
        }
    }

    fun saveExerciseCalories(exercise: Int) {
        viewModelScope.launch {
            preferencesDataSource.saveExerciseCalories(exercise)
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

