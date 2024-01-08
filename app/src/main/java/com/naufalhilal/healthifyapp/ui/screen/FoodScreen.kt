package com.naufalhilal.healthifyapp.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naufalhilal.healthifyapp.ui.theme.Secondary90
import com.naufalhilal.healthifyapp.viewmodel.FoodViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(navController: NavController, paddingValues: PaddingValues) {
    val foodViewModel: FoodViewModel = hiltViewModel()

    foodViewModel.getFoodRecommendations()

    val foods by foodViewModel.foods.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(foods) { foodPair ->
            val title = foodPair.first
            val description = "Calories: ${foodPair.second}"
            val status = foodViewModel.getFoodStatus(foodPair.second)

            ExpandableCard(
                title = title,
                description = description,
                status = status,
                additionalInfo = "Informasi tambahan untuk $title"
            )
        }
    }
}

@Composable
fun ExpandableCard(
    title: String,
    description: String,
    status: String,
    additionalInfo: String
) {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(containerColor = Secondary90),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Expand",
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        scope.launch {
                            Timber.d("Icon clicked")
                            Toast.makeText(
                                context,
                                "To Be Implemented",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
            // Group padding modifiers
            val paddingModifier = Modifier.padding(8.dp)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = paddingModifier
            )
            Text(
                text = "Status: $status",
                style = MaterialTheme.typography.titleMedium,
                modifier = paddingModifier
            )
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = additionalInfo,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = paddingModifier
                )
            }
        }
    }
}