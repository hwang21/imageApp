package com.s.popularimageapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.s.popularimageapp.Repository.Repository
import com.s.popularimageapp.api.ApiService

class ViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}