package com.example.booking.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.booking.data.BookingDatabase
import com.example.booking.data.Hotel
import com.example.booking.data.Reservation
import com.example.booking.reposytory.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservationViewModel(application: Application): AndroidViewModel(application) {
    val readAllReservation: LiveData<List<Reservation>>
    private val repository: HotelRepository
    init {
        val userDao = BookingDatabase.getInstance(application).bookingDao()
        repository = HotelRepository(userDao)
//        readAllReservation = repository.getAllReservations
        readAllReservation = repository.getReservations

    }




}