package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditTextEmail(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    trailingIcon: @Composable (() -> Unit)?,
    isError: Boolean = false,
    label: @Composable () -> Unit,
    icon: @Composable () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = isError,
        singleLine = true,
        leadingIcon = icon,
        trailingIcon = trailingIcon,
        label = label,
        modifier = modifier.padding(8.dp),
        textStyle = TextStyle(fontSize = 16.sp)
    )
}