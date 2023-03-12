package com.fuka.iknow.boradcast.reciever

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fuka.iknow.viewModels.EventViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
* Mitä luokka tekee ?
*
* tämä on tarkoitettu lisäämään toiminnallisuutta käyttäjälle näytettyyn ilmoitukseen (ilmoituspalkki)
* Nyt notifikaatio sisältää painikkeen jnka painamalla tapahtuman sisäinen tila/status päivitetään
* Tämä näytettiin opettajille viikkopalverissa jonka jälkeen tilaikoni poistettiin tapahtumalistalta
*
* 1. VAstaanota tieto totifikaatiopainikkeen painamistsesta
* 2. Hae kyseinen tapahtuma tietokannasta
* 3. Päivitä kyseisen tapahtuman tila ROOM tietokannassa
*
* */


class BroadcastActionNotificationReceiver: BroadcastReceiver() {
    val viewModel = EventViewModel(Application())

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