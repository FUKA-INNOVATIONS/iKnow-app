package com.fuka.iknow.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fuka.iknow.viewModels.EventViewModel
import androidx.compose.ui.res.stringResource
import com.fuka.iknow.R



/*
* Events näkymä
*
* Haetaan tahaptumat ROOM tietokannasta
* Näyttää oletuksena listan kaikista tapahtumista
* Tapahtumia voidaaan suodata painamalle niihin tarkoitettua painikkeita
* Käyttäjä on tietoiken mitkä tpahtumat ovat näytöllä ( filtteri painikkeen väri )
* Halutessaan käyttäjä pystyy postamaan valitsemansa tapahtuman jolloin se poistetaan ROOM tietokannasta pysyvästä
*
* Filteröinti korvataan  DropDown valikolla jatkokehtysvaiheessa jolloin valikon sisältö on dynaaminen
* sekä lisätään järkestämis vaihtoehtoja esim. päivämäärän/kollonajan/viimeisimmät   mukaan
*
*
*  >>>>     Jos haluat testata notifikaatiopainikkeen (tilan muutos) toimintaa
*           1. posita kommentit riveiltä 112-122
*           2. luo tapahtuma esim. Lentotilan kytkeminen
*           3. Avaa notifikaatiopalkki ja paina notifikaatiossa olevaa paniketta
*           4. Palaa sovelluksen Events näkymään niin näet että tapahtuman vasemmalla laidalla oleva ikonin väri on muuttunut
*/




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel = EventViewModel(context.applicationContext as Application)
    var broadcastActionList = viewModel.getBroadcastActions().observeAsState(listOf())
    var batteryLevelList = viewModel.getBroadcastActionsByType("batteryLow").observeAsState(listOf())
    var airplaneModeList = viewModel.getBroadcastActionsByType("airplaneMode").observeAsState(listOf())
    var wifiStateList = viewModel.getBroadcastActionsByType("WifiStateChanged").observeAsState(listOf())

    var activeList by remember { mutableStateOf(broadcastActionList) }

    val airplaneBtnActive = if (activeList == airplaneModeList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val batteryBtnActive = if (activeList == batteryLevelList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val wifiBtnActive = if (activeList == wifiStateList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val showAlBtnActive = if (activeList == broadcastActionList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary

    val actionTypes = broadcastActionList.value.map { it.type }.toSet().toList() // get action types


    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp), horizontalArrangement = Arrangement.SpaceBetween) {

            OutlinedButton(onClick = { activeList = airplaneModeList }, colors = ButtonDefaults.buttonColors(contentColor = airplaneBtnActive)) {
                Text(text = stringResource(R.string.airplane))
            }

            OutlinedButton(onClick = { activeList = batteryLevelList }, colors = ButtonDefaults.buttonColors(contentColor = batteryBtnActive)) {
                Text(text = stringResource(R.string.battery))
            }
            OutlinedButton(onClick = { activeList = wifiStateList }, colors = ButtonDefaults.buttonColors(contentColor = wifiBtnActive)) {
                Text(text = stringResource(R.string.wifi_state))
            }

            OutlinedButton(onClick = { activeList = broadcastActionList }, colors = ButtonDefaults.buttonColors(contentColor = showAlBtnActive)) {
                Text(text = stringResource(R.string.all))
            }

        }

        //Row(modifier = Modifier.height(50.dp)) { CustomDropdownMenu(eventTitles = listOf<String>("First", "Second", "Third")) }


        Scaffold(
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    items(activeList.value) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            ListItem(
                                //headlineText = { Text("Airplane mode changed") },
                                headlineText = { Text(it.type) },
                                //overlineText = { Text(it.type) },
                                supportingText = { Text(it.timestamp) },
                                /*leadingContent = {
                                    Icon(
                                        Icons.Filled.CheckCircle,
                                        contentDescription = "Event item status icon",
                                        tint = if (it.status) Color.Gray else Color.LightGray, // TODO: list item is not updated on status change
                                        modifier = Modifier.clickable {
                                            Log.d(TAG, "it.hashCode: ${it.intentHashcode.toString()}")
                                            viewModel.changeBroadcastActionStatus(it)
                                        }
                                    )
                                },*/
                                trailingContent = {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = stringResource(R.string.airplane_mode_event_icon_deletion),
                                        tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.clickable {
                                            viewModel.deleteBroadcastAction(it)
                                            Log.d("iKnow-app", it.status.toString())
                                        }
                                    )
                                }
                            )
                            Divider()
                        }
                    }
                }

            }
        )
    }


}