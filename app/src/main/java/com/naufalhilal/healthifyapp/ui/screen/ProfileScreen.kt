package com.naufalhilal.healthifyapp.ui.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.naufalhilal.healthifyapp.ui.route.Screen
import com.naufalhilal.healthifyapp.ui.theme.HealthifyAppTheme
import com.naufalhilal.healthifyapp.ui.theme.Primary40
import com.naufalhilal.healthifyapp.ui.theme.PurpleGrey40
import com.naufalhilal.healthifyapp.ui.theme.SurfaceContainer

@Composable
fun ProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ImageProfile()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Profile Name",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "username@email.com",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.EditProfileRoute.route)
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainer)
            ) {
                Text(
                    text = "Edit Profile",
                    color = Primary40
                )
            }
            Button(
                onClick = { /* TODO: Action */ },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainer)
            ) {
                Text(
                    text = "Sign Out",
                    color = Primary40
                )
            }
        }
    }
}

@Composable
fun ImageProfile() {
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

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    HealthifyAppTheme {
        ProfileScreen(
            navController = NavController(LocalContext.current),
            paddingValues = PaddingValues(16.dp)
        )
    }
}
