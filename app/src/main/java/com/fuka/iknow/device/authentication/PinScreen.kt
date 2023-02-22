package com.fuka.iknow.device.authentication

import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fuka.iknow.ui.theme.IKnowTheme
import com.fuka.iknow.ui.theme.ButtonWidth
import com.fuka.iknow.ui.theme.MarginDouble
import com.fuka.iknow.ui.theme.MarginQuad
import com.fuka.iknow.ui.theme.MaxTabletWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

@Composable
fun PinScreen(
    state: PinState,
    pinCallbacks: PinCallbacks,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier.width(MaxTabletWidth),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(horizontal = MarginQuad),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(MarginDouble))
            PasswordTextField(
                value = state.pin,
                onValueChange = { value -> pinCallbacks.onPinChange(value) },
                label = { Text(text = stringResource(id = R.string.pin)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                isError = state.pinError,
            )
            Spacer(modifier = Modifier.height(MarginQuad))
            Button(
                onClick = { pinCallbacks.onPinUnlockClick() },
                enabled = state.pinButtonEnabled,
                modifier = Modifier
                    .padding(end = MarginQuad)
                    .width(ButtonWidth)
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.ok)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PinScreenPreview() {
    IKnowTheme {
        PinScreen(
            state = PinState(
                pin = "",
                pinButtonEnabled = false,
                pinError = false,
            ),
            pinCallbacks = noOpPinCallbacks,
        )
    }
}