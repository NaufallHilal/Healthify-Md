package com.naufalhilal.healthifyapp.data.repository

import com.naufalhilal.healthifyapp.data.api.ApiService
import com.naufalhilal.healthifyapp.data.local.database.AppDatabase
import com.naufalhilal.healthifyapp.data.local.entity.HealthData
import com.naufalhilal.healthifyapp.data.model.HealthDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HealthDataRepo @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {

    // Menambahkan data kesehatan ke database lokal
    suspend fun addHealthData(healthData: HealthData) {
        appDatabase.healthDataDao().insertHealthData(healthData) // Menggunakan DAO dari database
    }

    // Mengupdate data kesehatan di database lokal
    suspend fun updateHealthData(healthData: HealthData) {
        appDatabase.healthDataDao().updateHealthData(healthData) // Menggunakan DAO dari database
    }

    // Menghapus data kesehatan dari database lokal
    suspend fun deleteHealthData(healthData: HealthData) {
        appDatabase.healthDataDao().deleteHealthData(healthData) // Menggunakan DAO dari database
    }

    // Mendapatkan semua data kesehatan dari database lokal
    fun getAllHealthData(): Flow<List<HealthData>> {
        return flow {
            val healthDataList =
                appDatabase.healthDataDao().getAllHealthData() // Menggunakan DAO dari database
            emit(healthDataList)
        }.flowOn(Dispatchers.IO)
    }

    // Mendapatkan data kesehatan berdasarkan ID dari database lokal
    fun getHealthDataById(id: Int): Flow<HealthData> {
        return flow {
            val healthData =
                appDatabase.healthDataDao().getHealthDataById(id) // Menggunakan DAO dari database
            emit(healthData)
        }.flowOn(Dispatchers.IO)
    }

    // Mendapatkan semua data kesehatan dari API
    suspend fun fetchAllHealthData(): HealthDataResponse {
        return apiService.getHealthData(url = String())
    }

    // Mendapatkan data kesehatan berdasarkan ID dari API
    suspend fun fetchHealthDataById(id: Int): HealthData {
        return apiService.getHealthDataById(id)
    }
}
