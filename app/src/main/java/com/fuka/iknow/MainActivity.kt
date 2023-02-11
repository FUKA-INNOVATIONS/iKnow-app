package com.fuka.iknow

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import com.fuka.iknow.data.database.dataBase.iKnowDatabase
import com.fuka.iknow.data.database.entity.BroadcastAction
import com.fuka.iknow.ui.theme.IKnowTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

class iKnowViewModel (application: Application) : AndroidViewModel(application) {
    private val db = iKnowDatabase.get_iKnowDatabase(application)

    fun getBroadcastActions() : LiveData<List<BroadcastAction>> {
        val allBroadcastActions = db.iKnowDao.getBroadcastActions()
        return allBroadcastActions
    }

    fun addBroadcastAction(broadcastAction: BroadcastAction) {
        viewModelScope.launch (Dispatchers.IO) {
            db.iKnowDao.insertOrUpdateBroadcastAction(broadcastAction)
        }
    }

    fun addFiveBroadcastActions() {
        var timestamp: String

        viewModelScope.launch (Dispatchers.IO) {

            for (i in 1..5) {
                timestamp = ofPattern("dd-MM-yyyy HH:mm:ss.SSSSSS")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())

                db.iKnowDao.insertOrUpdateBroadcastAction(
                    BroadcastAction(
                        0,
                        "Video captured",
                        "Camera",
                        timestamp
                    )
                )
            }
        }
    }
}



class MainActivity : ComponentActivity() {
    val viewModel: iKnowViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppNav(viewModel)
        }
    }
}

@Composable
fun MainAppNav(viewModel: iKnowViewModel) {
    IKnowTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppBody(viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBody(viewModel: iKnowViewModel) {
    val broadcastActionList = viewModel.getBroadcastActions().observeAsState(listOf())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("iKnow")
                }
            )
        },

        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                items(broadcastActionList.value) {
                    // Content here
                    Text(text = "testiteksti 123454321")
                    Text("BroadcastAction: ${it.type}")
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                FloatingActionButton(
                    onClick = { viewModel.addFiveBroadcastActions() },
                    modifier = Modifier
                        .padding(all = 15.dp)
                ) {
                    Text("Add five more!")
                }
            }
        }
    )
}