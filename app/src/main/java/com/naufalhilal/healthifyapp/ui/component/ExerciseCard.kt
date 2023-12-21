package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.ui.theme.Primary40
import com.naufalhilal.healthifyapp.ui.theme.Secondary90
import com.naufalhilal.healthifyapp.viewmodel.HomeViewModel

@Composable
fun ExerciseCard(viewModel: HomeViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.getExerciseCalories()
    }

    val exerciseCaloriesState by viewModel.exerciseCaloriesState.collectAsState()

    if (showDialog) {
        AddExerciseDialog(
            onConfirm = {
                viewModel.saveExerciseCalories(it)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.FitnessCenter,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Exercise",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = exerciseCaloriesState?.toString() ?: "N/A",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Cal",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Column {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            showDialog = true
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun AddExerciseDialog(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var exerciseCalories by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Exercise Calories") },
        confirmButton = {
            Button(
                onClick = {
                    if (exerciseCalories.isNotEmpty()) {
                        onConfirm(exerciseCalories.toInt())
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary40)
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Primary40)
            ) {
                Text("Cancel")
            }
        },
        text = {
            TextField(
                value = exerciseCalories,
                onValueChange = { exerciseCalories = it },
                label = { Text("Exercise Calories") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}
