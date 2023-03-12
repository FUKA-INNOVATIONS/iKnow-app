package com.fuka.iknow.screens

import android.app.Application
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.*
import com.fuka.iknow.R
import com.fuka.iknow.ui.theme.IKnowTheme
import com.fuka.iknow.viewModels.EventViewModel



/*
* Setting näkymä
*
* Käyttäjä pystyy postamaan kaikki tapahtumat painamalla poista kaikki pahtuman painikett
* Käyttäjän on vahvistettava poistoa jolloin häntä varoitetaan ettei poistettuja tapahtumia ei pysty positon jäkeen palauttamaan
* Kaikki tapahtuamat poistetaan ROOM tietokannasta pysyvästi
*
* Jatkokehtiys
*   1. Käyttäjä valitsee listalta mistä tapahtumista haluaa ilmoituksen ja mitkä talletataan teitokantaan
*   2. Käyttäjä asettaa/muuttaa Pinkoodun sovelluksen avaamiseen
*   3. Käyttäjä aktivoi/deaktivoi bio tunnistautumista
*
*   ... ja lisää kivoja asioita :)
*
* */



@Composable
fun SettingsScreen() {

    // Select animation
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.settings)
    )

    // Animation loop
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    
    IKnowTheme {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var deleteConf by remember {
                mutableStateOf(false) // Initially dialog is closed
            }

            // Display lottie animation
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {deleteConf = true}) {
                Text(text = stringResource(R.string.delete_events_button), textAlign = TextAlign.Center)
            }

            if (deleteConf) {
                DeleteConfirmation() {
                    deleteConf = false
                }
            }
        }
    }
}

// Delete button confirmation screen and function
@Composable
fun DeleteConfirmation(
    cornerRadius: Dp = 12.dp,
    deleteButtonColor: Color = Color(0xFFFF0000),
    cancelButtonColor: Color = Color(0xFF35898F),
    titleTextStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 20.sp
    ),
    messageTextStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    buttonTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    onDismiss: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val viewModel = EventViewModel(context as Application)

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
                        text = stringResource(R.string.delete_events_title),
                        textAlign = TextAlign.Center,
                        style = titleTextStyle
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 20.dp),
                    text = stringResource(R.string.delete_events_description1),
                    style = messageTextStyle
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 20.dp),
                    text = stringResource(R.string.delete_events_description2),
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
                                    .makeText(
                                        context,
                                        (R.string.cancel_deletion_toast),
                                        Toast.LENGTH_SHORT
                                    )
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
                            text = stringResource(R.string.cancel_deletion_button),
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
                                    .makeText(
                                        context,
                                        R.string.delete_events_toast,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                viewModel.deleteAllBroadcastActions()
                                onDismiss()
                            }
                            .background(
                                color = deleteButtonColor,
                                shape = RoundedCornerShape(buttonCorner)
                            )
                            .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.deletion_confirmation_button),
                            style = buttonTextStyle,
                            color = Color.White,
                        )
                    }

                }
            }

        }

    }
}