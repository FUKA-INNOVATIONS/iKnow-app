package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import com.fuka.iknow.R
import com.fuka.iknow.UiText
import com.fuka.iknow.data.database.dataBase.iKnowDatabase
import com.fuka.iknow.service.BroadcastActionNotificationService
import com.fuka.iknow.viewModels.DatabaseViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/***
 * TODO: string resources
 *
 * // Maybe works?
 * val TAG = UiText.StringResource(resId = R.string.tag).toString()
 *
 * Doesn't work
 * val notif: String = UiText.StringResource(resId = R.string.airplane_mode_changed_notification).toString()
 */
val TAG = "iKnow-app"
class AirPlaneBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "AirPlaneBroadcastReceiver > onCreate()")

        val service = BroadcastActionNotificationService(context)
        service.showNotification(33, "Airplane mode changed", "Action: ${intent.action}", intent.hashCode())

        val viewModel = DatabaseViewModel(context.applicationContext as Application)
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