package com.fuka.iknow.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.fuka.iknow.MainActivity
import com.fuka.iknow.R
import com.fuka.iknow.boradcast.reciever.BroadcastActionNotificationReceiver
import com.fuka.iknow.boradcast.reciever.TAG

class BroadcastActionNotificationService(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(id: Int, title: String, content: String, intentHashcode: Int) {
        Log.d(TAG, "BroadcastActionNotificationService.showNotification()")

        // action on notification click/tap
        val activityIntent = Intent(context, MainActivity::class.java)

        // OR pendingIntent -> A description of an Intent and target action to perform with it.
        // Can be triggered by external sources, iex. Broadcast, service OR cancel erc..  > Flags
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            id, // Unique notification id (request code), can be use for example to update etc..
            activityIntent,
            // From on API v (30+ or so) we need to specify flag mutable/immutable meaning ..
            // that the receiver of this intent can or can't directly manipulate this intent
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_UPDATE_CURRENT else 0
        )

        val checkIntent = PendingIntent.getBroadcast(
            context,
            id,
            // Intent(context, LikeNotificationReceiver::class.java),
            // Intent with extra data
            Intent(context, BroadcastActionNotificationReceiver::class.java).apply {
                putExtra("intentHashcode", intentHashcode) // Sent to the receiver inside intent param
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_UPDATE_CURRENT else 0
        )

        // Build the notification
        val notification = NotificationCompat.Builder(context, BROADCAST_ACTION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_eco_24)
            .setContentTitle(title)
            .setContentText("$content")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_launcher_foreground,
                "Mark as checked!",
                checkIntent
            )
            .build()

        // Show the notification
        notificationManager.notify(id, notification)
    }

    companion object {
        const val BROADCAST_ACTION_CHANNEL_ID = "broadcast_action_channel"
    }

}