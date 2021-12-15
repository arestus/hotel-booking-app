package com.example.booking.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.booking.data.BookingDatabase
import com.example.booking.data.Hotel
import com.example.booking.reposytory.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel(application: Application): AndroidViewModel(application) {

     val readAllHotels: LiveData<List<Hotel>>
    private val repository: HotelRepository
    init {
        val userDao = BookingDatabase.getInstance(application).bookingDao()
        repository = HotelRepository(userDao)
        readAllHotels = repository.getHotels
    }




}