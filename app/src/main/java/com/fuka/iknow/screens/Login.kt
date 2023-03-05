package com.fuka.iknow.screens

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun LoginScreen(onClick: () -> Unit) {
    Button(onClick = {onClick()}, shape = CutCornerShape(19)) {
        Text(text = "Login")
    }
}