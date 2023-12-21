package com.naufalhilal.healthifyapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naufalhilal.healthifyapp.data.local.dao.DiaryDao
import com.naufalhilal.healthifyapp.data.local.dao.EatTimeDao
import com.naufalhilal.healthifyapp.data.local.dao.FoodDao
import com.naufalhilal.healthifyapp.data.local.dao.HealthDataDao
import com.naufalhilal.healthifyapp.data.local.entity.Diary
import com.naufalhilal.healthifyapp.data.local.entity.EatTime
import com.naufalhilal.healthifyapp.data.local.entity.Food
import com.naufalhilal.healthifyapp.data.local.entity.HealthData

@Database(
    entities = [Diary::class, EatTime::class, Food::class, HealthData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
    abstract fun eatTimeDao(): EatTimeDao
    abstract fun foodDao(): FoodDao
    abstract fun healthDataDao(): HealthDataDao

}