package com.naufalhilal.healthifyapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor() : ViewModel() {

    private val _foods = MutableStateFlow<List<Pair<String, Double>>>(emptyList())
    val foods: StateFlow<List<Pair<String, Double>>> = _foods

    private val dummyFoods = listOf(
        Pair("Gemblong", 25.52),
        Pair("Kentang", 98.89),
        Pair("Jamur Portabella", 79.4),
        Pair("Tiwul Instan", 39.23),
        Pair("Kacang Tunggak Blackeyes", 98.9),
        Pair("Zucchini", 61.45),
        Pair("Kuda Daging", 46.3),
        Pair("Susu Kerbau", 23.11),
        Pair("Blueberry Beku", 415.8),
        Pair("Mcdonald's Kue Panas", 14.91),
        Pair("Ikan Teri", 214.5)
    )

    fun getFoodRecommendations() {
        _foods.value = dummyFoods
    }

    fun getFoodStatus(calories: Double): String {
        return when {
            calories <= 50 -> "Low Calories"
            calories <= 100 -> "Enough Calories"
            else -> "High Calories"
        }
    }
}