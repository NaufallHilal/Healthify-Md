package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.naufalhilal.healthifyapp.R

@Composable
fun EditTextPassword(
    modifier: Modifier=Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    trailingIcon:@Composable (()->Unit)?,
    isError: Boolean = false,
    visualTransformation: VisualTransformation,
    supportingText:@Composable ()->Unit,
) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = isError,
            singleLine = true,
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    tint = if (isError) Color.Red else Color.Gray,
                )
            },
            visualTransformation=visualTransformation,
            trailingIcon=trailingIcon,
            label ={
                Text(stringResource(id = R.string.edt_password_hint), color = Color.Gray )
            },
            modifier = modifier.padding(8.dp),
            supportingText=supportingText
        )
}