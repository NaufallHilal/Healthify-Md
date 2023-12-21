package com.naufalhilal.healthifyapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naufalhilal.healthifyapp.ui.component.ExerciseCard
import com.naufalhilal.healthifyapp.ui.component.HealthStatusCard
import com.naufalhilal.healthifyapp.ui.component.WeightTrackCard
import com.naufalhilal.healthifyapp.ui.theme.HealthifyAppTheme
import com.naufalhilal.healthifyapp.ui.theme.Secondary90
import com.naufalhilal.healthifyapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            val exerciseCalories by viewModel.exerciseCaloriesState.collectAsState()
            HealthStatusCard(5000, 90, exerciseCalories ?: 0)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StepsCard(10)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ExerciseCard(viewModel)
                }
            }
            WeightTrackCard(weights = listOf(70f, 71f, 72f, 73f, 74f, 75f, 76f))
        }
    }
}

@Composable
fun StepsCard(steps: Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Secondary90,
        modifier = Modifier
            .fillMaxSize(1f)
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
                Icon(
                    Icons.Default.DirectionsWalk,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Steps",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = steps.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HealthifyAppTheme {
        HomeScreen(
            navController = NavController(LocalContext.current),
            paddingValues = PaddingValues(16.dp)
        )
    }
}
