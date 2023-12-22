package com.naufalhilal.healthifyapp.di

import android.content.Context
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.data.pref.UserPreference
import com.naufalhilal.healthifyapp.data.pref.dataStore

object injection {
    fun provideRepository(context: Context): HealthifyRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        return HealthifyRepository.getInstance(pref)
    }
}