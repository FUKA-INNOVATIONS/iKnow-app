package com.fuka.iknow.device.authentication

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fuka.iknow.ui.theme.IKnowTheme
import com.fuka.iknow.ui.theme.MarginDouble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    var passwordVisible: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    PasswordTextField(
        value = value,
        onValueChange = onValueChange,
        passwordVisible = passwordVisible,
        onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        label = label,
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    OutlinedTextField(
        value = value,
        label = label,
        enabled = enabled,
        isError = isError,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = onTogglePasswordVisibility,
            ) {
                Crossfade(
                    targetState = passwordVisible,
                ) { visible ->
                    when (visible) {
                        true -> Icon(Icons.Filled.Lock, "icon")
                        false -> Icon(Icons.Filled.Home, "icon2")
                    }
                }
            }
        },
        onValueChange = onValueChange,
        modifier = modifier,
        colors = colors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    IKnowTheme {
        var value by remember {
            mutableStateOf("")
        }
        Surface(modifier = Modifier.width(360.dp)) {
            PasswordTextField(
                value = value,
                onValueChange = { newValue -> value = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MarginDouble),
                label = { Text(text = "PIN") },
            )
        }
    }
}