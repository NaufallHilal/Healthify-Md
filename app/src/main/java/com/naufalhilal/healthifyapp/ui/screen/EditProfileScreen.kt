package com.naufalhilal.healthifyapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.naufalhilal.healthifyapp.ui.theme.Primary40
import com.naufalhilal.healthifyapp.ui.theme.PurpleGrey40
import com.naufalhilal.healthifyapp.ui.theme.SurfaceContainer

@Composable
fun EditProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isSnackbarVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            ImageEditProfile()

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "To Be Implemented",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainer)
            ) {
                Text(
                    text = "Change Picture",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Primary40
                )
            }

            ProfileField(
                value = username,
                onValueChange = { username = it },
                label = "Enter Username"
            )
            ProfileField(value = email, onValueChange = { email = it }, label = "Enter Email")
            ProfileField(
                value = selectedGender,
                onValueChange = { selectedGender = it },
                label = "Gender"
            )
            ProfileField(value = height, onValueChange = { height = it }, label = "Height (cm)")
            ProfileField(value = weight, onValueChange = { weight = it }, label = "Weight (kg)")

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "To Be Implemented",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainer)
            ) {
                Text(text = "Save Changes", color = Primary40)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileField(value: String, onValueChange: (String) -> Unit, label: String) {
    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceContainer,
            unfocusedContainerColor = SurfaceContainer,
            disabledContainerColor = SurfaceContainer,
            cursorColor = Primary40,
            focusedIndicatorColor = Primary40,
            unfocusedIndicatorColor = Primary40,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}


@Composable
fun ImageEditProfile() {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://www.chennaigrocers.com/cdn/shop/files/Green-Apple.jpg?v=1694866262")
            .crossfade(true)
            .build(),
        contentDescription = "Image Profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(150.dp)
            .border(width = 2.dp, color = PurpleGrey40, shape = CircleShape)
    )
}
