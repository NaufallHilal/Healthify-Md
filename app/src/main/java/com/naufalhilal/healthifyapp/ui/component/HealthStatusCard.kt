package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.ui.theme.Primary40
import com.naufalhilal.healthifyapp.ui.theme.Secondary90
import com.naufalhilal.healthifyapp.ui.theme.SurfaceContainer

@Composable
fun HealthStatusCard(goal: Int, food: Int, exerciseCalories: Int) {
    var remaining by remember { mutableStateOf((goal - food) + exerciseCalories) }
    remaining = maxOf(remaining, 0)
    LaunchedEffect(exerciseCalories) {
        remaining = ((goal - food) + exerciseCalories)
    }
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Secondary90,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "$remaining",
                        fontSize = 24.sp,
                        color = Primary40,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 40.dp)
                    )
                    Text(
                        text = "Remaining",
                        fontSize = 16.sp,
                        color = Primary40,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 40.dp)
                    )
                    val calorieBar = remaining.toFloat() / goal.toFloat()
                    CircularProgressIndicator(
                        progress = calorieBar,
                        modifier = Modifier.size(150.dp),
                        color = Primary40,
                        strokeWidth = 12.dp,
                        trackColor = SurfaceContainer,
                        strokeCap = StrokeCap.Butt,
                    )
                }

                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = "Calories",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Remaining = Goal - Food + Exercises",
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "Base Goal",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = goal.toString(), fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(34.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Fastfood, contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "Food",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = food.toString(), fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(34.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FitnessCenter, contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "Exercise",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = exerciseCalories.toString(), fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
