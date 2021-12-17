package com.example.booking.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.booking.data.BookingDatabase

import com.example.booking.data.LocalUser
import com.example.booking.reposytory.HotelRepository



class OtherUsersViewModel(application: Application): AndroidViewModel(application) {

     val usersList: LiveData<List<LocalUser>>
    private val repository: HotelRepository

    init {
        val userDao = BookingDatabase.getInstance(application).bookingDao()
        repository = HotelRepository(userDao)
        usersList = repository.getLocalUsers
    }
}