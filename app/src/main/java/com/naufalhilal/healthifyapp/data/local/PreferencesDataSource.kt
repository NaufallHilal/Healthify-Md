package com.naufalhilal.healthifyapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    /**
     * Get user's authentication token
     *
     * @return Flow
     */
    fun getAuthToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    /**
     * Save the user's authentication token to preferences
     *
     * @param token Authentication token
     */
    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token


        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    fun getUserId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }

    suspend fun saveExerciseCalories(exercise: Int) {
        dataStore.edit { preferences ->
            preferences[EXERCISE_CALORIES_KEY] = exercise.toString()
            Timber.d("Saved exercise calories: $exercise")
        }
    }

    fun getExerciseCalories(): Flow<Int?> {
        return dataStore.data.map { preferences ->
            val exercise = preferences[EXERCISE_CALORIES_KEY]?.toIntOrNull()
            Timber.d("Retrieved exercise calories: $exercise")
            exercise
        }
    }

    suspend fun saveSelectedDate(date: String) {
        dataStore.edit { preferences ->
            preferences[DATE_KEY] = date
            Timber.d("Saved selected date: $date")
        }
    }

    fun getSelectedDate(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[DATE_KEY]
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token_data")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val EXERCISE_CALORIES_KEY = stringPreferencesKey("exercise_calories")
        private val DATE_KEY = stringPreferencesKey("date_key")
    }
}