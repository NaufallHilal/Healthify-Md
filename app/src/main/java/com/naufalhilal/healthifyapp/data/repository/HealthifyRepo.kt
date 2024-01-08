package com.naufalhilal.healthifyapp.data.repository

import com.naufalhilal.healthifyapp.data.local.PreferencesDataSource
import com.naufalhilal.healthifyapp.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

class HealthifyRepo @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) {

    suspend fun saveSession(user: UserModel): Boolean {
        return try {
            preferencesDataSource.saveSession(user)
            true
        } catch (e: Exception) {
            Timber.e(e, "Error saving session")
            false
        }
    }

    fun getSession(): Flow<UserModel> {
        return preferencesDataSource.getSession()
            .catch { e ->
                Timber.e(e, "Error getting session")
                emit(UserModel("", "", 0, false))  // Provide default value or handle accordingly
            }
    }

    suspend fun logout() {
        try {
            preferencesDataSource.logout()
        } catch (e: Exception) {
            Timber.e(e, "Error logging out")
        }
    }
}
