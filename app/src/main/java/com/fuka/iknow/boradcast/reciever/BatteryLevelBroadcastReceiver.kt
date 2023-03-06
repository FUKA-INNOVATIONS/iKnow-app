package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.service.BroadcastActionNotificationService
import com.fuka.iknow.viewModels.DatabaseViewModel

class BatteryLevelBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "BatteryLevelBroadcastReceiver > ${intent.action}")

        val viewModel = DatabaseViewModel(context.applicationContext as Application)
        viewModel.addBroadcastAction(intent.extras.toString(), "batteryLow", intent.hashCode())

    }
}