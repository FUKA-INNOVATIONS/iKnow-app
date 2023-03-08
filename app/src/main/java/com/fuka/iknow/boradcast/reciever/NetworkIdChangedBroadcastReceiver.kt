package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.viewModels.DatabaseViewModel

class NetworkIdChangedBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "NetworkIdChangedBroadcastReceiver > ${intent.action}")
        Log.d(TAG, "NetworkIdChangedBroadcastReceiver > ${intent.extras}")

        val viewModel = DatabaseViewModel(context.applicationContext as Application)
        viewModel.addBroadcastAction(intent.extras.toString(), "WifiIdChanged", intent.hashCode())

    }
}