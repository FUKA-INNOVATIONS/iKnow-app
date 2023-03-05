package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.viewModels.DatabaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BroadcastActionNotificationReceiver: BroadcastReceiver() {
    val viewModel = DatabaseViewModel(Application())

    override fun onReceive(context: Context, intent: Intent?) {

        val intentHashcode = intent?.getIntExtra("intentHashcode", 30)

        CoroutineScope(Dispatchers.IO).launch {
            val actionToUpdate = viewModel.getBroadcastActionHashCoded(intentHashcode)
            viewModel.changeBroadcastActionStatus(actionToUpdate)
        }


        // change action status > repo
        Log.d(TAG, "change broadcast action status change from notification")
        Log.d(TAG, "intent hashcode in BroadcastActionNotificationReceiver $intentHashcode")
    }
}