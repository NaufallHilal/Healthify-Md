package com.naufalhilal.healthifyapp.ui.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.R
import com.naufalhilal.healthifyapp.ui.component.HealthifyButton

@Composable
fun LandingPage(
    modifier: Modifier=Modifier,
    navigateToLogin:()->Unit,
    navigateToRegister:()->Unit,
    ){
    val endColor = Color(red = 0, green = 42, blue = 40)
    val startColor = Color(0xFFFFFFFF)
    val gradientBrush = Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center,
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Lets Start Your Healthy Journey", style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp), modifier = modifier)
            Text(text = "HealthifyApp", style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp), modifier = modifier.padding(top = 8.dp), color = Color.DarkGray)
            HealthifyButton(
                text = "Login",
                onClick = navigateToLogin,
                modifier = modifier.width(150.dp).padding(top = 32.dp, bottom = 0.dp)
            )
            HealthifyButton(
                text = "Register",
                onClick = navigateToRegister,
                modifier = modifier.width(150.dp).padding( bottom = 0.dp),
            )
        }
    }
}