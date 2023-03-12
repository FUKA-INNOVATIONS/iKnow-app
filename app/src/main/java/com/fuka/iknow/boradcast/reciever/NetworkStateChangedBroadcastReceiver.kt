package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.viewModels.EventViewModel


/*
* Mitä luokka tekee ?
*
* 1. Vastaanota tieto Wifi tilan muuttumisesta
* 2. Tallenna tapahtuma ROOM tietokantaan
*
* */

class NetworkStateChangedBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "NetworkStateChangedBroadcastReceiver > ${intent.action}")
        Log.d(TAG, "NetworkStateChangedBroadcastReceiver > ${intent.extras}")

        val viewModel = EventViewModel(context.applicationContext as Application)
        viewModel.addBroadcastAction(intent.extras.toString(), "WifiStateChanged", intent.hashCode())

    }
}