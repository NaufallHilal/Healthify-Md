package com.naufalhilal.healthifyapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naufalhilal.healthifyapp.data.HealthifyRepository
import com.naufalhilal.healthifyapp.di.injection
import com.naufalhilal.healthifyapp.ui.screen.addFood.AddFoodViewModel
import com.naufalhilal.healthifyapp.ui.screen.createFood.CreateFoodViewModel
import com.naufalhilal.healthifyapp.ui.screen.dairyActivity.DairyViewModel
import com.naufalhilal.healthifyapp.ui.screen.home.HomeViewModel
import com.naufalhilal.healthifyapp.ui.screen.login.LoginViewModel
import com.naufalhilal.healthifyapp.ui.screen.register.RegisterViewModel

class ViewModelFactory(private val repository: HealthifyRepository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddFoodViewModel::class.java) -> {
                AddFoodViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CreateFoodViewModel::class.java) -> {
                CreateFoodViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DairyViewModel::class.java) -> {
                DairyViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context):ViewModelFactory{
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}