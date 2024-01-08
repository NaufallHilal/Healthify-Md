package com.naufalhilal.healthifyapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naufalhilal.healthifyapp.data.model.CheckDiaryResponse
import com.naufalhilal.healthifyapp.data.model.DiaryWithFoodNames
import com.naufalhilal.healthifyapp.data.model.HealthDataResponse
import com.naufalhilal.healthifyapp.viewmodel.DiaryViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    navigateToAddFood: (String, Int) -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    var checkDate by remember { mutableStateOf(LocalDate.now().toString()) }
    var isLoading by remember { mutableStateOf(true) }
    var healthData by remember { mutableStateOf(HealthDataResponse()) }
    healthData = viewModel.uiStateHealthData.collectAsState().value
    var checkDiary by remember { mutableStateOf(CheckDiaryResponse()) }
    checkDiary = viewModel.uiStateCheckDiary.collectAsState().value
    var userId by remember { mutableStateOf(0) }
    var createDiary by remember { mutableStateOf(CheckDiaryResponse()) }
    createDiary = viewModel.uiStateCreateDiary.collectAsState().value
    var foodFromDiary by remember { mutableStateOf(DiaryWithFoodNames()) }
    foodFromDiary = viewModel.uiStateGetFoodFromDiary.collectAsState().value

    viewModel.getSession()
    viewModel.session.collectAsState().value.let {
        userId = it.userId
        LaunchedEffect(key1 = it.userId) {
            viewModel.getHealthDataFromUserId(it.userId)
        }
        LaunchedEffect(key1 = it.userId, key2 = checkDate, key3 = createDiary.diaryId) {
            viewModel.checkDiary(it.userId, checkDate)
        }
    }
    if (checkDiary.diaryId != null) {
        viewModel.getFoodFromDiary(checkDiary.diaryId!!)
    }
    if (healthData.message != null && checkDiary.message != null) {
        isLoading = false
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())

        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Health Dairy",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )

            //Date ROW
            HomeSection(title = "Hari dan Tanggal") {
                var selectedDay by remember { mutableStateOf(LocalDate.now().dayOfWeek.toString()) }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = modifier
                ) {
                    val weekDays = getCurrentWeeksDays()
                    weekDays.forEach { (day, date) ->
                        item {
                            Column(
                                modifier = modifier
                                    .background(
                                        color = if (day == LocalDate.now().dayOfWeek.toString()) Color(
                                            0xFF3A4C2A
                                        ) else Color(red = 0, green = 47, blue = 46)
                                    )
                                    .clickable {
                                        selectedDay = day
                                        checkDate = date.second
                                    }
                                    .border(
                                        width = 4.dp,
                                        color = if (day == selectedDay) Color(
                                            red = 0,
                                            green = 145,
                                            blue = 141
                                        ) else Color.Transparent,
                                    )
                                    .padding(horizontal = 8.dp, vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = date.first,
                                    fontSize = 16.sp,
                                    style = TextStyle(color = Color.White)
                                )
                                Text(
                                    text = day,
                                    fontSize = 8.sp,
                                    style = TextStyle(color = Color.White),
                                    modifier = Modifier.paddingFromBaseline(
                                        top = 16.dp,
                                        bottom = 8.dp
                                    )
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(start = 16.dp, top = 8.dp)
                ) {
                    Text(
                        text = "Warna untuk hari ini :",
                        fontSize = 12.sp,
                        style = TextStyle(color = Color.Gray, fontStyle = FontStyle.Italic),
                        modifier = Modifier.paddingFromBaseline(top = 8.dp, bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(Color(0xFF3A4C2A), shape = CircleShape)
                            .padding(start = 4.dp)
                    )
                }
            }
            //calories info
            HomeSection(title = "Sisa Kalori") {
                CalorieInfo(calories = healthData.healthDataList?.calories)
            }
            Divider(
                modifier = modifier.padding(horizontal = 12.dp),
                color = Color(red = 0, green = 47, blue = 46)
            )
            //diary and eatTime
            if (checkDiary.message == "Diary not found for the given user and date") {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        viewModel.createDiary(
                            userId = userId,
                            date = checkDate,
                            calories = healthData.healthDataList?.calories!!
                        )
                    }) {
                        Text(text = "Create Diary")
                    }
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Sarapan", style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "0", modifier = modifier
                            .padding(top = 16.dp, end = 16.dp)
                    )

                }
                foodFromDiary.diaryDetailsWithFood?.forEach {
                    if (it.eat_time == "Sarapan") {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                text = it.food_name.toString(),
                                modifier = modifier
                                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "0", modifier = modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
                Divider(modifier = modifier.padding(horizontal = 16.dp), color = Color.Gray)
                Text(text = "TAMBAH MAKANAN",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color(0xFF3A4C2A)),
                    modifier = modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 12.dp)
                        .clickable { navigateToAddFood("Sarapan", checkDiary.diaryId!!) })
                Divider(
                    modifier = modifier.padding(horizontal = 12.dp),
                    color = Color(red = 0, green = 47, blue = 46)
                )
//////////////////
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Lunch", style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "0", modifier = modifier
                            .padding(top = 16.dp, end = 16.dp)
                    )

                }
                foodFromDiary.diaryDetailsWithFood?.forEach {
                    if (it.eat_time == "Lunch") {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                text = it.food_name.toString(),
                                modifier = modifier
                                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "0", modifier = modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
                Divider(modifier = modifier.padding(horizontal = 16.dp), color = Color.Gray)
                Text(text = "TAMBAH MAKANAN",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color(0xFF3A4C2A)),
                    modifier = modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 12.dp)
                        .clickable { navigateToAddFood("Lunch", checkDiary.diaryId!!) })
                Divider(
                    modifier = modifier.padding(horizontal = 12.dp),
                    color = Color(red = 0, green = 47, blue = 46)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Dinner", style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "0", modifier = modifier
                            .padding(top = 16.dp, end = 16.dp)
                    )

                }
                foodFromDiary.diaryDetailsWithFood?.forEach {
                    if (it.eat_time == "Dinner") {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                text = it.food_name.toString(),
                                modifier = modifier
                                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "0", modifier = modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
                Divider(modifier = modifier.padding(horizontal = 16.dp), color = Color.Gray)
                Text(text = "TAMBAH MAKANAN",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color(0xFF3A4C2A)),
                    modifier = modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 12.dp)
                        .clickable { navigateToAddFood("Dinner", checkDiary.diaryId!!) })
                Divider(
                    modifier = modifier.padding(start = 12.dp, bottom = 16.dp),
                    color = Color(red = 0, green = 47, blue = 46)
                )
            }
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier,
            )
        }
    }
}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        SectionText(title = title, modifier)
        content()
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}


@Composable
fun CalorieInfo(modifier: Modifier = Modifier, calories: Int?) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        item {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = calories.toString(), fontSize = 24.sp)
                Text(
                    text = "Sasaran",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                )
            }
        }
        item {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "-", fontSize = 24.sp)
            }
        }
        item {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "N/A", fontSize = 24.sp)
                Text(
                    text = "Makanan",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                )
            }
        }
        item {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "=", fontSize = 24.sp)
            }
        }
        item {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = calories.toString(),
                    fontSize = 24.sp,
                    style = TextStyle(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    text = "Sisa",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentWeeksDays(): Map<String, Pair<String, String>> {
    val currentDateTime = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd MMM", Locale.getDefault())
    val tanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

    val daysInWeek = mutableMapOf<String, Pair<String, String>>()
    var currentDate = currentDateTime.with(DayOfWeek.MONDAY)

    for (i in 0 until 7) {
        val formattedDate = currentDate.format(formatter)
        val checktanggal = currentDate.format(tanggal)
        val dayOfWeek = currentDate.dayOfWeek.toString()
        daysInWeek[dayOfWeek] = Pair(formattedDate, checktanggal)
        currentDate = currentDate.plusDays(1)
    }

    return daysInWeek
}
