package com.naufalhilal.healthifyapp.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naufalhilal.healthifyapp.data.entities.Diary
import com.naufalhilal.healthifyapp.data.entities.EatTime
import com.naufalhilal.healthifyapp.data.entities.Food
import com.naufalhilal.healthifyapp.data.entities.HealthData
import com.naufalhilal.healthifyapp.data.entities.User
import com.naufalhilal.healthifyapp.data.local.DiaryDao
import com.naufalhilal.healthifyapp.data.local.EatTimeDao
import com.naufalhilal.healthifyapp.data.local.FoodDao
import com.naufalhilal.healthifyapp.data.local.HealthDataDao
import com.naufalhilal.healthifyapp.data.local.UserDao

@Database(entities = [User::class, Diary::class, EatTime::class, Food::class, HealthData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun diaryDao(): DiaryDao
    abstract fun eatTimeDao(): EatTimeDao
    abstract fun foodDao(): FoodDao
    abstract fun healthDataDao(): HealthDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
