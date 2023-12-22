package com.naufalhilal.healthifyapp.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.healthifyapp.R
import com.naufalhilal.healthifyapp.di.injection
import com.naufalhilal.healthifyapp.ui.ViewModelFactory
import com.naufalhilal.healthifyapp.ui.common.UiState
import com.naufalhilal.healthifyapp.ui.component.EditTextEmail
import com.naufalhilal.healthifyapp.ui.component.EditTextPassword
import com.naufalhilal.healthifyapp.ui.component.HealthifyButton

@Composable
fun RegisterScreen(
    modifier: Modifier=Modifier,
    navigateToLogin:()->Unit,
    viewModel: RegisterViewModel= androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(injection.provideRepository(LocalContext.current))
    )
){
    var inputEmail by remember{ mutableStateOf(TextFieldValue("")) }
    var inputPassword by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var inputName by remember { mutableStateOf(TextFieldValue("")) }
    var isError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val errorMessage by viewModel.error.collectAsState()

    viewModel.uiState.collectAsState().value.let {uistate->
        when(uistate){
            is UiState.Loading->{

            }
            is UiState.Success->{
                isLoading=false
                isError=false
                if (uistate.data.message!=null){
                    Toast.makeText(LocalContext.current,uistate.data.message,Toast.LENGTH_SHORT).show()
                }
            }
            is UiState.Error->{
                isLoading=false
                isError=true
            }
        }
    }
    val trailingIconEmail= @Composable{
        IconButton(onClick = { inputEmail= TextFieldValue("") }) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear toggle",
                tint = Color.Black,
                modifier= modifier.size(24.dp)
            )
        }
    }
    val trailingIconName= @Composable{
        IconButton(onClick = { inputName= TextFieldValue("") }) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear toggle",
                tint = Color.Black,
                modifier= modifier.size(24.dp)
            )
        }
    }
    val trailingIconPasswordVisualTransformation = @Composable{
        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
            Icon(
                painterResource(id =if (passwordVisibility) R.drawable.invisible else R.drawable.view, ),
                contentDescription = "Visibility toggle",
                tint = Color.Black,
                modifier = modifier.size(24.dp)
            )
        }
    }
    val startColor = Color(red = 0, green = 42, blue = 40)
    val endColor = Color(0xFFFFFFFF)// Ganti dengan warna akhir yang diinginkan

    // Membuat gradien dengan menggunakan Brush.linearGradient
    val gradientBrush = Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Lets Sign You Up!", style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp))
            Text(text = "HealthifyApp", style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp), modifier = modifier.padding(bottom = 24.dp))
            EditTextEmail(
                value = inputName,
                onValueChange = {newValue->inputName=newValue},
                trailingIcon = if (inputName.text.isNotBlank()) trailingIconName else null ,
                label = { Text("Masukkan nama anda", color = Color.Gray) },
                icon = {Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name Icon",
                    tint = if (isError) Color.Red else Color.Gray,
                )}
                )

            EditTextEmail(value = inputEmail, onValueChange = {
                    newValue->inputEmail=newValue
            }, trailingIcon = if (inputEmail.text.isNotBlank()) trailingIconEmail else null, modifier = modifier, label = {Text(
                stringResource(id = R.string.edt_email_hint), color = Color.Gray )},
                icon = {Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = if (isError) Color.Red else Color.Gray,
                )},)

            EditTextPassword(value = inputPassword, isError = isError, onValueChange = {
                    newValue->inputPassword=newValue
            }, visualTransformation = if (passwordVisibility){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            }, trailingIcon =  if (inputPassword.text.isNotBlank()) trailingIconPasswordVisualTransformation else null,supportingText = {if(isError) Text(text = errorMessage, color = Color.Red)}, modifier = modifier)
            HealthifyButton(
                text = "Register",
                enabled = inputEmail.text.isNotBlank() && inputPassword.text.isNotBlank(),
                modifier = modifier,
                onClick = {
                    isLoading = true
                    viewModel.postRegister(inputName.text, inputEmail.text, inputPassword.text)
                }
            )
            Row {
                Text(text = "Already have an account? " ,Modifier.padding(vertical = 13.dp), style = TextStyle(fontSize = 12.sp,fontWeight = FontWeight.Bold))
                Text(text = "Login", modifier = modifier
                    .clickable { navigateToLogin() }
                    .padding(vertical = 13.dp), style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray))
            }
            CircularProgressIndicator(modifier=modifier.alpha(if (isLoading)1f else 0f))
        }
    }
}