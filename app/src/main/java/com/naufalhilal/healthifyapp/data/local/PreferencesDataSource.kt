package com.naufalhilal.healthifyapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.naufalhilal.healthifyapp.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class PreferencesDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    // User session related preferences
    suspend fun saveSession(user: UserModel): Boolean {
        if (user.email.isEmpty() || user.token.isEmpty() || user.userId.toString().isEmpty()) {
            Timber.e("Missing required fields in user object")
            return false
        }
        return try {
            dataStore.edit { preferences ->
                preferences[EMAIL_KEY] = user.email
                preferences[TOKEN_KEY] = user.token
                preferences[USERID_KEY] = user.userId
                preferences[IS_LOGIN_KEY] = true
            }
            true
        } catch (e: Exception) {
            Timber.e(e, "Error saving session")
            false
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[EMAIL_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[USERID_KEY] ?: 0,
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Other preferences functionalities
    fun getAuthToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

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

    suspend fun saveWeight(weight: Float, day: Int) {
        val weightKey = stringPreferencesKey("weight_day_$day")
        try {
            dataStore.edit { preferences ->
                preferences[weightKey] = weight.toString()
                Timber.d("Saved weight for day $day: $weight")
            }
        } catch (e: Exception) {
            Timber.e(e, "Error saving weight for day $day")
        }
    }

    suspend fun getWeight(day: Int): Flow<List<Float>> {
        val weightKey = stringPreferencesKey("weight_day_$day")
        return dataStore.data.map { preferences ->
            val weight = preferences[weightKey]?.toFloatOrNull()
            weight?.let { listOf(it) } ?: emptyList()
        }
    }

    companion object {
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USERID_KEY = intPreferencesKey("userId")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val EXERCISE_CALORIES_KEY = stringPreferencesKey("exercise_calories")
        private val DATE_KEY = stringPreferencesKey("date_key")
        private val WEIGHT_KEY_PREFIX = stringPreferencesKey("weight_day_")
    }
}