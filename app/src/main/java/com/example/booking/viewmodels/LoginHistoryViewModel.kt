package com.example.booking.viewmodels

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.booking.data.BookingDatabase

import com.example.booking.data.LoginHistory
import com.example.booking.reposytory.HotelRepository
import retrofit2.http.Tag

class LoginHistoryViewModel (application: Application): AndroidViewModel(application){
    val historyList: LiveData<List<LoginHistory>>
    private val repository: HotelRepository

    init {
        val userDao = BookingDatabase.getInstance(application).bookingDao()
        repository = HotelRepository(userDao)
        historyList = repository.getLoginHistory

    }
}