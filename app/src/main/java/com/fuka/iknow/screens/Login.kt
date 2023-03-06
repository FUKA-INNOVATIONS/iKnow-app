package com.fuka.iknow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fuka.iknow.ui.theme.IKnowTheme


@Composable
fun LoginScreen(onClick: () -> Unit) {
    IKnowTheme {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "iKnow",
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {onClick()}, shape = CutCornerShape(19)) {
                Text(text = "Authenticate", textAlign = TextAlign.Center)
            }
        }
    }
}