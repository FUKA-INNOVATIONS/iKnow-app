package com.fuka.iknow.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Not sure if needed in the future
/*
class DatabaseViewModelFactory(var application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            return DatabaseViewModel(application) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}
 */