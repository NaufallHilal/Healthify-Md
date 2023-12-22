package com.naufalhilal.healthifyapp.ui.screen.createFood

import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.di.injection
import com.naufalhilal.healthifyapp.ui.ViewModelFactory
import com.naufalhilal.healthifyapp.ui.common.UiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFoodScreen(modifier: Modifier=Modifier, navigateBack:()->Boolean,
                     viewModel: CreateFoodViewModel =androidx.lifecycle.viewmodel.compose.viewModel(
                         factory = ViewModelFactory(injection.provideRepository(LocalContext.current))
                     ),) {
    var foodName by remember { mutableStateOf(TextFieldValue("")) }
    var calories by remember { mutableStateOf(TextFieldValue("")) }
    var fat by remember { mutableStateOf(TextFieldValue("")) }
    var carbohydrate by remember { mutableStateOf(TextFieldValue("")) }
    var protein by remember { mutableStateOf(TextFieldValue("")) }
    var userId:Int
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    viewModel.uiState.collectAsState().value.let {
        when(it){
            is UiState.Loading->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier,
                    )
                }
                viewModel.getSession()
            }
            is UiState.Success->{
                userId = it.data.userId
                Column {
                    TopAppBar(title = {
                        Text(
                            text = "Create Food",
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start
                        )
                    },
                        navigationIcon = { IconButton(
                            onClick = { navigateBack() },
                        ) {
                            Icon(
                                imageVector= Icons.Default.ArrowBack,contentDescription = "back"
                            )
                        }},
                        actions = {IconButton(onClick = {
                            viewModel.viewModelScope.launch {
                               val success= viewModel.createFood(
                                    user_id = userId,
                                    calories = calories.text.toInt(),
                                    protein = protein.text.toInt(),
                                    carbohydrate=carbohydrate.text.toInt(),
                                    fat = fat.text.toInt(),
                                    food_name = foodName.text)
                                if (success.foodDetails?.foodName!=null){
                                    snackbarText = success.foodDetails.foodName+" telah berhasil dibuat!"
                                    snackbarVisible = true
                                }else {
                                    snackbarText = "Maaf data kamu belum bisa di simpan"
                                    snackbarVisible = true
                                }
                                navigateBack()
                            }
                        }
                            ,enabled = foodName.text.isNotBlank() && fat.text.isNotBlank() && carbohydrate.text.isNotBlank() && protein.text.isNotBlank()) {
                            Icon(
                                imageVector= Icons.Default.Check,contentDescription = "submit"
                            )
                        }}
                    )
                    if (snackbarVisible) {
                        Toast.makeText(LocalContext.current, snackbarText, Toast.LENGTH_SHORT)
                            .show()
                    }
                    Row(modifier = modifier.padding(16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically, ) {
                        Text(text = "Nama Makanan")
                        Spacer(modifier = Modifier.weight(1f))
                        TextField(value = foodName, onValueChange = {newValue->foodName=newValue}, singleLine = true, modifier = modifier.wrapContentWidth(Alignment.End) )
                    }
                    Divider(modifier = modifier, color = Color.Gray)
                    Row(modifier = modifier.padding(16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically,) {
                        Text(text = "Lemak")
                        Spacer(modifier = Modifier.weight(1f))
                        TextField(value = fat, onValueChange = {newValue->fat=newValue},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
                    }
                    Divider(modifier = modifier, color = Color.Gray)
                    Row(modifier = modifier.padding(16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically,) {
                        Text(text = "Calories")
                        Spacer(modifier = Modifier.weight(1f))
                        TextField(value = calories, onValueChange = {newValue->calories=newValue},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
                    }
                    Divider(modifier = modifier, color = Color.Gray)
                    Row(modifier = modifier.padding(16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically,) {
                        Text(text = "Karbohidrat")
                        Spacer(modifier = Modifier.weight(1f))
                        TextField(value = carbohydrate, onValueChange = {newValue->carbohydrate=newValue},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
                    }
                    Divider(modifier = modifier, color = Color.Gray)
                    Row(modifier = modifier.padding(16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically,) {
                        Text(text = "Protein")
                        Spacer(modifier = Modifier.weight(1f))
                        TextField(value = protein, onValueChange = {newValue->protein=newValue},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true, modifier = Modifier
                            .background(Color.Transparent) // Latar belakang transparan
                            .fillMaxWidth())
                    }
                }
            }
            is UiState.Error->{
                
            }
        }
    }
}
