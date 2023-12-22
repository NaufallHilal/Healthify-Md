package com.naufalhilal.healthifyapp.data

import com.naufalhilal.healthifyapp.data.pref.UserModel
import com.naufalhilal.healthifyapp.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field

class HealthifyRepository private constructor(
    private val userPreference: UserPreference,
){
    suspend fun saveSession(user: UserModel):Boolean {
        if (userPreference.saveSession(user)){
            return true
        }
        return true
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }


    companion object{
        @Volatile
        private var instance: HealthifyRepository? = null
        fun getInstance(
            userPreference: UserPreference,
        ):HealthifyRepository=
            instance ?: synchronized(this) {
                instance ?: HealthifyRepository(userPreference)
            }.also { instance = it }
    }
}