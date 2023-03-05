package com.fuka.iknow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.fuka.iknow.boradcast.reciever.TAG
import com.fuka.iknow.service.BroadcastActionNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IKnowApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel")
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                // Channel id could be stored in values>strings = localization etc..
                BroadcastActionNotificationService.BROADCAST_ACTION_CHANNEL_ID,
                "Broadcast_action",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used to change status of broadcast action in the database"

            // Register the channel with the system
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}