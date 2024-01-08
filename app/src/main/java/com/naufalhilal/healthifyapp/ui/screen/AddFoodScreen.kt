package com.naufalhilal.healthifyapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.healthifyapp.ui.common.UiState
import com.naufalhilal.healthifyapp.viewmodel.AddFoodViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(
    eatTime: String,
    diaryId: Int,
    navigateToCreateFood: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddFoodViewModel = hiltViewModel()
) {
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFood()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier,
                    )
                }
            }

            is UiState.Success -> {
                val foodAll = uiState.data
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = eatTime,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                    LazyColumn(modifier = modifier.weight(weight = 1f)) {
                        items(foodAll.listFoods[0]) {
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(72.dp)
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                                    .background(
                                        Color.LightGray,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(modifier = modifier.padding(horizontal = 13.dp)) {
                                    Column {
                                        Text(text = it.foodName.toString())
                                        Text(text = it.calories.toString() + " Calories")

                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Button(
                                        onClick = {
                                            viewModel.viewModelScope.launch {
                                                val success = viewModel.addFoodToDiary(
                                                    diaryId = diaryId,
                                                    eatTime = eatTime,
                                                    foodId = it.foodId!!
                                                )
                                                if (success.message!! == "Added food to today's diary") {
                                                    snackbarText =
                                                        "Food telah berhasil ditambahkan!"
                                                    snackbarVisible = true
                                                } else {
                                                    snackbarText =
                                                        "Maaf data kamu belum bisa di simpan"
                                                    snackbarVisible = true
                                                }
                                            }
                                        },
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "addFood",
                                            modifier = modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    if (snackbarVisible) {
                        Toast.makeText(LocalContext.current, snackbarText, Toast.LENGTH_SHORT)
                            .show()
                        snackbarVisible = false
                    }
                    Button(
                        onClick = { navigateToCreateFood() }, enabled = true, modifier = modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Tambah Makanan",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                }
            }

            is UiState.Error -> {

            }
        }
    }

}
