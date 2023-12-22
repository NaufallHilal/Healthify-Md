package com.naufalhilal.healthifyapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.naufalhilal.healthifyapp.di.injection
import com.naufalhilal.healthifyapp.ui.ViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.naufalhilal.healthifyapp.ui.common.UiState
import com.naufalhilal.healthifyapp.ui.navigation.Screen

@Composable
fun HomeScreen(
    modifier: Modifier=Modifier,
    landingPageRoute: String,
    navController: NavHostController,
    viewModel: HomeViewModel=androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(injection.provideRepository(LocalContext.current))
    ),
    ){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState->
        when(uiState){
            is UiState.Loading->{
                viewModel.getSession()
            }
            is UiState.Success->{
                LaunchedEffect(uiState.data){
                    if (uiState.data.token.isEmpty()){
                        navController.popBackStack()
                        navController.navigate(landingPageRoute)
                    }else{
                        navController.navigate(Screen.Diary.route)
                    }
                }
            }
            is UiState.Error->{

            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Column {
            Text(text = "This is Home of Healthify!")
            Button(onClick = { viewModel.logout() }) {
                Text(text = "Logout")
            }
        }
    }
}