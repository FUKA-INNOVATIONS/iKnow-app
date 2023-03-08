package com.fuka.iknow.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fuka.iknow.ui.theme.IKnowTheme

@Composable
fun SettingsScreen() {
    IKnowTheme {
        Column (
            modifier = Modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            var deleteConf by remember {
                mutableStateOf(false) // Initially dialog is closed
            }
            Button(onClick = {deleteConf = true}) {
                Text(text = "Delete events", textAlign = TextAlign.Center)
            }
            if (deleteConf) {
                DeleteConfirmation() {
                    deleteConf = false
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmation(
    cornerRadius: Dp = 12.dp,
    deleteButtonColor: Color = Color(0xFFFF0000),
    cancelButtonColor: Color = Color(0xFF35898F),
    titleTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.87f),
        fontSize = 20.sp
    ),
    messageTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.95f),
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    buttonTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    onDismiss: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val buttonCorner = 6.dp

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = cornerRadius)
        ) {

            Column(modifier = Modifier.padding(all = 16.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 6.dp,
                        alignment = Alignment.Start
                    )
                ) {
                    Text(
                        text = "Delete Events?",
                        textAlign = TextAlign.Center,
                        style = titleTextStyle
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 20.dp),
                    text = "Are you sure you want to delete events?",
                    style = messageTextStyle
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.End
                    )
                ) {
                    // Cancel button
                    Box(
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = interactionSource
                            ) {
                                Toast
                                    .makeText(context, "Cancel", Toast.LENGTH_SHORT)
                                    .show()
                                onDismiss()
                            }
                            .border(
                                width = 1.dp,
                                color = cancelButtonColor,
                                shape = RoundedCornerShape(buttonCorner)
                            )
                            .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    ) {
                        Text(
                            text = "Cancel",
                            style = buttonTextStyle,
                            color = cancelButtonColor
                        )
                    }

                    Spacer(modifier = Modifier.size(71.dp))
                    // Delete button
                    Box(
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = interactionSource
                            ) {
                                Toast
                                    .makeText(context, "Delete", Toast.LENGTH_SHORT)
                                    .show()
                                onDismiss()
                            }
                            .background(
                                color = deleteButtonColor,
                                shape = RoundedCornerShape(buttonCorner)
                            )
                            .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    ) {
                        Text(
                            text = "Delete",
                            style = buttonTextStyle,
                            color = Color.White
                        )
                    }

                }
            }

        }

    }
}