package com.naufalhilal.healthifyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.entities.Diary
import com.naufalhilal.healthifyapp.data.entities.HealthData

@Dao
interface HealthDataDao {
    @Insert
    fun insertHealthData(healthData: HealthData)

    @Update
    fun updateHealthData(healthData: HealthData)

    @Delete
    fun deleteHealthData(healthData: HealthData)

    @Query("SELECT * FROM health_data WHERE health_data_id = :id")
    fun getHealthDataById(id: Int): HealthData

    @Query("SELECT * FROM health_data WHERE user_id = :userId")
    fun getHealthDataByUserId(userId: Int): List<HealthData>
}