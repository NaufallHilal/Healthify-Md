package com.naufalhilal.healthifyapp.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.ui.theme.Primary100
import com.naufalhilal.healthifyapp.ui.theme.Primary40
import com.naufalhilal.healthifyapp.ui.theme.Secondary90

@Composable
fun WeightTrackCard(weights: List<Float>) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        AddWeightDialog(
            onConfirm = { weight, day ->
                showDialog = false
            },
            onDismiss = { showDialog = false }
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
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Use Box to align the "+" icon to the top-end corner
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
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

            Text(
                text = "Weight Tracker",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            weights.forEachIndexed { index, weight ->
                WeightEntryBar(day = index + 1, weight = weight)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun WeightEntryBar(day: Int, weight: Float) {
    val color = calculateColorForWeight(weight)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = color)
    ) {
        Text(
            text = "Day $day",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
        )
        Text(
            text = "$weight kg",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
}

fun calculateColorForWeight(weight: Float): Color {
    val progress = weight / 100
    val startColor = Primary100
    val endColor = Primary40
    return lerp(startColor, endColor, progress)
}

fun lerp(start: Color, end: Color, fraction: Float): Color {
    return Color(
        lerp(start.red, end.red, fraction),
        lerp(start.green, end.green, fraction),
        lerp(start.blue, end.blue, fraction),
        lerp(start.alpha, end.alpha, fraction)
    )
}

fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + fraction * (end - start)
}

@Composable
fun AddWeightDialog(
    onConfirm: (Float, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var weight by remember { mutableStateOf(0f) }
    var day by remember { mutableStateOf(1) }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add Weight") },
        text = {
            Column {
                TextField(
                    value = weight.toString(),
                    onValueChange = { weight = it.toFloatOrNull() ?: 0f },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = day.toString(),
                    onValueChange = { day = it.toIntOrNull() ?: 1 },
                    label = { Text("Day") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(weight, day)
                    Toast.makeText(context, "To Be Implemented", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary40)
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Primary40)
            ) {
                Text("Cancel")
            }
        }
    )
}
