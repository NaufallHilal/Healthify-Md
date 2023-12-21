package com.naufalhilal.healthifyapp.di

import android.content.Context
import androidx.room.Room
import com.naufalhilal.healthifyapp.data.local.dao.DiaryDao
import com.naufalhilal.healthifyapp.data.local.dao.EatTimeDao
import com.naufalhilal.healthifyapp.data.local.dao.FoodDao
import com.naufalhilal.healthifyapp.data.local.dao.HealthDataDao
import com.naufalhilal.healthifyapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDiaryDao(AppDatabase: AppDatabase): DiaryDao = AppDatabase.diaryDao()

    @Provides
    fun provideEatTimeDao(AppDatabase: AppDatabase): EatTimeDao = AppDatabase.eatTimeDao()

    @Provides
    fun provideFoodDao(AppDatabase: AppDatabase): FoodDao = AppDatabase.foodDao()

    @Provides
    fun provideHealthDataDao(AppDatabase: AppDatabase): HealthDataDao = AppDatabase.healthDataDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "healthify_database"
        ).build()
    }
}