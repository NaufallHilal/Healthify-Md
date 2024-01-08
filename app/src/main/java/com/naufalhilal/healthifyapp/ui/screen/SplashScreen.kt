package com.naufalhilal.healthifyapp.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val startColor = Color(red = 0, green = 42, blue = 40)
    val endColor = Color(0xFFFFFFFF) // Ganti dengan warna akhir yang diinginkan

    // Membuat gradien dengan menggunakan Brush.linearGradient
    val gradientBrush = Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navigateToHome()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.healthify),
                contentDescription = "Healthify Logo",
                modifier = modifier
                    .scale(scale.value)
                    .padding(start = 6.dp)
                    .size(100.dp)
            )
            Text(
                text = "HealthifyApp",
                style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp),
                modifier = modifier.padding(top = 8.dp),
                color = Color.DarkGray
            )
        }
    }
}

