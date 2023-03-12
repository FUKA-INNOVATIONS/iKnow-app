package com.fuka.iknow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.fuka.iknow.R
import com.fuka.iknow.ui.theme.IKnowTheme



/*
* Login näkymä
*
* Käyttäjä on pakotettu käyttämään sormenjäki tunnistina kirjautukseen sovellukseen
* Jatkokehityksessä lisätään pin koodi kurjautuminen
*
*   HUOM !! Ennen kuin käytät sovellusta
*   1. Siirry puhelimen asetuksiin
*   2. Laita sormenjälkitunnistin päälle ja rekisterö sellainen omaa sormea tai esmulaattorin sormea käyttäen :)
*   3. Avaa sovellut ja tunnistausu JA nauti sen tuomasta tuvallisuuden tunteista sekä eduista =D
*
*
* */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onClick: () -> Unit) {
    // Select animation
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.animation)
    )

    // Animation loop
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    IKnowTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.padding(60.dp),
                    title = {
                        Text(
                            stringResource(R.string.app_name),
                            fontSize = 40.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        ){
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            )
        }
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display lottie animation
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {onClick()}) {
                Text(text = stringResource(R.string.authenticate), textAlign = TextAlign.Center)
            }
        }
    }
}