package com.naufalhilal.healthifyapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.naufalhilal.healthifyapp.ui.common.UiState
import com.naufalhilal.healthifyapp.ui.component.HealthifyButton
import com.naufalhilal.healthifyapp.ui.route.Screen
import com.naufalhilal.healthifyapp.ui.screen.home.LandingViewModel

@Composable
fun LandingPage(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.White, Color(0xFF002A28))
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val mediumTextStyle = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp)
            Text(text = "Lets Start Your Healthy Journey", style = mediumTextStyle)
            Text(
                text = "HealthifyApp",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            val buttonModifier = Modifier
                .width(150.dp)
                .padding(top = 32.dp, bottom = 0.dp)
            HealthifyButton(text = "Login", onClick = navigateToLogin, modifier = buttonModifier)
            HealthifyButton(
                text = "Register",
                onClick = navigateToRegister,
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 0.dp)
            )
        }
    }
}

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    landingPageRoute: String,
    navController: NavHostController,
    viewModel: LandingViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is UiState.Success -> {
            LaunchedEffect(uiState.data.token) {
                if (uiState.data.token.isNotEmpty()) {
                    navController.navigate(Screen.MainScreenRoute.route)
                } else {
                    navController.popBackStack()
                    navController.navigate(landingPageRoute)
                }
            }
        }

        is UiState.Error -> {
            // Handle error state
        }

        is UiState.Loading -> {
            viewModel.getSession()
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "This is Home of Healthify!")
            Button(onClick = { viewModel.logout() }) {
                Text(text = "Logout")
            }
        }
    }
}
