package com.naufalhilal.healthifyapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.naufalhilal.healthifyapp.data.entity.Diary
import com.naufalhilal.healthifyapp.data.entity.EatTime
import com.naufalhilal.healthifyapp.data.entity.Food
import com.naufalhilal.healthifyapp.data.entity.HealthData
import com.naufalhilal.healthifyapp.data.entity.User
import com.naufalhilal.healthifyapp.data.local.DiaryDao
import com.naufalhilal.healthifyapp.data.local.EatTimeDao
import com.naufalhilal.healthifyapp.data.local.FoodDao
import com.naufalhilal.healthifyapp.data.local.HealthDataDao
import com.naufalhilal.healthifyapp.data.local.UserDao
import com.naufalhilal.healthifyapp.utils.TypeConverter

@Database(entities = [User::class, Diary::class, EatTime::class, Food::class, HealthData::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
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
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "healthify_database"
            ).build()
        }
    }
}

