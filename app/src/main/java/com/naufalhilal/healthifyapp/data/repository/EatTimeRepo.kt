package com.naufalhilal.healthifyapp.data.repository

import com.naufalhilal.healthifyapp.data.api.ApiService
import com.naufalhilal.healthifyapp.data.local.database.AppDatabase
import com.naufalhilal.healthifyapp.data.local.entity.EatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EatTimeRepo @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {

    // Mendapatkan semua entri waktu makan berdasarkan diary ID dari database lokal
    fun getEatTimesByDiaryId(diaryId: Int): Flow<List<EatTime>> {
        return flow {
            val eatTimes = appDatabase.eatTimeDao()
                .getEatTimesByDiaryId(diaryId) // Menggunakan DAO dari database
            emit(eatTimes)
        }.flowOn(Dispatchers.IO)
    }

    // Menambahkan entri waktu makan ke database lokal
    suspend fun addEatTime(eatTime: EatTime) {
        appDatabase.eatTimeDao().insertEatTime(eatTime) // Menggunakan DAO dari database
    }

    // Mendapatkan semua entri waktu makan dari API
    suspend fun fetchAllEatTimesFromApi(): List<EatTime> {
        return apiService.getAllEatTimes()
    }
}
