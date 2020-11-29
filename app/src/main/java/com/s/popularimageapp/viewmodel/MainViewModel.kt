package com.s.popularimageapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.s.popularimageapp.utils.Resource
import com.s.popularimageapp.Repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository): ViewModel() {

    fun getData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}