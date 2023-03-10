package com.fuka.iknow

import android.Manifest
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fuka.iknow.boradcast.reciever.*
import com.fuka.iknow.navigation.NavigationPage
import com.fuka.iknow.screens.LoginScreen
import com.fuka.iknow.ui.theme.IKnowTheme
import dagger.hilt.android.AndroidEntryPoint




/*
*
* Käyttäjä on pakotettu käyttämään sormenjäki tunnistina kirjautukseen sovellukseen ( max API v. 30) muuten sovellus mahdollisesti kaatuu > tiedostettu bugi
* Jatkokehityksessä lisätään pinKoodilla kurjautumita ja tarkistetaan tukeeko käyttäjän laite sormenjäljellä tunnistautumista
*
*   HUOM !! Ennen kuin käytät sovellusta
*
*   1. Siirry puhelimen asetuksiin
*   2. Laita sormenjälkitunnistin päälle ja rekisterö sellainen omaa sormea tai esmulaattorin sormea käyttäen :)
*   3. Avaa sovellut ja tunnistausu JA nauti sen tuomasta tuvallisuuden tunteista sekä eduista =D
*
*   * Jatkokehtiys > Kofiguroitavissa myös sovelluksen Settings näkymässä
*   1. Käyttäjä valitsee listalta mistä tapahtumista haluaa ilmoituksen ja mitkä talletataan teitokantaan
*   2. Käyttäjä asettaa/muuttaa Pinkoodun sovelluksen avaamiseen
*   3. Käyttäjä aktivoi/deaktivoi bio tunnistautumista
*
* */






@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var brAirplane: BroadcastReceiver
    private lateinit var brBattery: BroadcastReceiver
    private lateinit var brWifiState: BroadcastReceiver
    private lateinit var brWifiId: BroadcastReceiver
    private var cancellationSignal: CancellationSignal? = null
    private lateinit var mContext: Context

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContent {
            IKnowTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LoginScreen(onClick = { launchBiometric() })
                }
            }
        }
    }

    // Biometric handler for success and errors
    private val authenticationCallback: android.hardware.biometrics.BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : android.hardware.biometrics.BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: android.hardware.biometrics.BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity, getText(R.string.authentication_success), Toast.LENGTH_SHORT).show()
                setContent {
                    IKnowTheme {
                        NavigationPage()
                    }
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@MainActivity, "${getText(R.string.authentication_error)}$errorCode", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
            }
        }

    // Check Biometric support
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isDeviceSecure) {
            return true
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            return false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    // Creates biometric prompt screen
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun launchBiometric() {
        if (checkBiometricSupport()) {
            val biometricPrompt = android.hardware.biometrics.BiometricPrompt.Builder(this)
                .apply {
                    setTitle(getText(R.string.biometric_auth_title))
                    setSubtitle(getText(R.string.biometric_auth_description))
                    setConfirmationRequired(false)
                    setNegativeButton(getText(R.string.cancel_authentication), mainExecutor) { _, _, ->
                        Toast.makeText(
                            this@MainActivity,
                            getText(R.string.authentication_cancelled),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.build()
            biometricPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)
        }
    }

    //Biometric cancellation
    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Toast.makeText(this, getText(R.string.authentication_cancelled_signal), Toast.LENGTH_SHORT).show()
        }

        return cancellationSignal as CancellationSignal
    }

    override fun onResume() {
        super.onResume()
        brAirplane = AirPlaneBroadcastReceiver()
        brBattery = BatteryLevelBroadcastReceiver()
        brWifiState = NetworkStateChangedBroadcastReceiver()
        brWifiId = NetworkIdChangedBroadcastReceiver()

        val filterAirplane = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val filterBatteryLow = IntentFilter(Intent.ACTION_BATTERY_LOW)
        val filterNetworkStateChanged = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        val filterNetworkIdChanged = IntentFilter(WifiManager.NETWORK_IDS_CHANGED_ACTION)

        val listenToBroadcastsFromOtherApps = false
        val receiverFlags =
            if (listenToBroadcastsFromOtherApps) { ContextCompat.RECEIVER_EXPORTED }
            else { ContextCompat.RECEIVER_NOT_EXPORTED }

        ContextCompat.registerReceiver(this, brAirplane, filterAirplane, receiverFlags)
        ContextCompat.registerReceiver(this, brBattery, filterBatteryLow, receiverFlags)
        ContextCompat.registerReceiver(this, brWifiState, filterNetworkStateChanged, receiverFlags)
        ContextCompat.registerReceiver(this, brWifiId, filterNetworkIdChanged, receiverFlags)
    }

    /*override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }*/

}