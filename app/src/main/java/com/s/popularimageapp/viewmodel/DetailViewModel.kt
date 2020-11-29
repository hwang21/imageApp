package com.s.popularimageapp.viewmodel

import androidx.lifecycle.ViewModel
import com.s.popularimageapp.model.Datum

class DetailViewModel:  ViewModel() {

    private lateinit var _data: Datum

    fun data() = _data

    fun updateData(data: Datum) {
        _data = data
    }
}