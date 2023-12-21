package com.naufalhilal.healthifyapp.data.repository

import com.naufalhilal.healthifyapp.data.api.ApiService
import com.naufalhilal.healthifyapp.data.local.database.AppDatabase
import com.naufalhilal.healthifyapp.data.local.entity.Diary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiaryRepo @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {

    // Mendapatkan semua diary dari database lokal
    fun getAllDiaries(): Flow<List<Diary>> {
        return flow {
            val diaries = appDatabase.diaryDao().getAllDiaries() // Menggunakan DAO dari database
            emit(diaries)
        }.flowOn(Dispatchers.IO)
    }

    // Mendapatkan diary berdasarkan ID dari database lokal
    fun getDiaryById(id: Int): Flow<Diary> {
        return flow {
            val diary = appDatabase.diaryDao().getDiaryById(id) // Menggunakan DAO dari database
            emit(diary)
        }.flowOn(Dispatchers.IO)
    }

    // Mendapatkan semua diary dari API
    suspend fun fetchAllDiaries(): List<Diary> {
        return apiService.getAllDiaries()
    }

    // Mendapatkan diary berdasarkan ID dari API
    suspend fun fetchDiaryById(id: Int): Diary {
        return apiService.getDiaryById(id)
    }
}
