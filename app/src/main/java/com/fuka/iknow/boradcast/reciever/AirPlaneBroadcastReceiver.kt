package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.service.BroadcastActionNotificationService
import com.fuka.iknow.viewModels.EventViewModel

val TAG = "iKnow-app"

/*
* Mitä luokka tekee ?
*
* 1. Vastaanota tieto Lentotilan muuttumisesta
* 2. Luo ja näytä notifikaatio käyttäjälle
* 3. Tallenna tapahtuma ROOM tietokantaan
* 4. Mahdollisesti > Näytä Toast ilmoitus käyttäjälle, tällä hetkellä ei ole kytketty päälle (rivi 45)
*
*   HUOM!
*   Sovellus mahdollisesti kaatuu jos API versio > 30 (mahdollinen bugi)
*
* */
class AirPlaneBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "AirPlaneBroadcastReceiver > onCreate()")

        val service = BroadcastActionNotificationService(context)
        service.showNotification(33, "Airplane mode changed", "Action: ${intent.action}", intent.hashCode())

        val viewModel = EventViewModel(context.applicationContext as Application)
        viewModel.addBroadcastAction(intent.extras.toString(), "airplaneMode", intent.hashCode())

        Log.d(TAG, "intent: $intent")
        Log.d(TAG, "intent extras: ${intent.extras}")
        Log.d(TAG, "context: $context")

        Log.d(TAG, "intent.hashCode: ${intent.hashCode()}")
        Log.d(TAG, "intent.extras.hashCode: ${intent.extras.hashCode()}")

        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                //Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }

}