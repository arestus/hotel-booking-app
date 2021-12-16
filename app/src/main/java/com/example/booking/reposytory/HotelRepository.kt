package com.example.booking.reposytory

import androidx.lifecycle.LiveData
import com.example.booking.data.BookingDao
import com.example.booking.data.Hotel
import com.example.booking.data.LocalUser
import com.example.booking.data.LoginHistory

class HotelRepository(private val hotelDao: BookingDao) {

    val getHotels:LiveData<List<Hotel>> = hotelDao.getHotels()
    val getLocalUsers:LiveData<List<LocalUser>> = hotelDao.getLocalUsers()
    val getLoginHistory:LiveData<List<LoginHistory>> = hotelDao.getLoginHistory()
//    suspend fun getHotels(hotels: LiveData<List<Hotel>>) {
//        hotelDao.getHotels(hotels)
//    }

}
