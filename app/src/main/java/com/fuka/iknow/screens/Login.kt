package com.fuka.iknow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlin.math.max
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.fuka.iknow.R
import com.fuka.iknow.navigation.AppNavigation
import com.fuka.iknow.ui.theme.IKnowTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onClick: () -> Unit) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.animation)
    )
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
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {onClick()}) {
                Text(text = "Authenticate", textAlign = TextAlign.Center)
            }
        }
    }
}