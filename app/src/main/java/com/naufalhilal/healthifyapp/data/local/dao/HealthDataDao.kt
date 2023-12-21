package com.naufalhilal.healthifyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.local.entity.HealthData

@Dao
interface HealthDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHealthData(healthData: HealthData)

    @Update
    fun updateHealthData(healthData: HealthData)

    @Delete
    fun deleteHealthData(healthData: HealthData)

    @Query("SELECT * FROM health_data")
    fun getAllHealthData(): List<HealthData>

    @Query("SELECT * FROM health_data WHERE health_data_id = :id")
    fun getHealthDataById(id: Int): HealthData
}