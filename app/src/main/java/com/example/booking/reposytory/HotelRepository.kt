package com.example.booking.reposytory

import androidx.lifecycle.LiveData
import com.example.booking.data.BookingDao
import com.example.booking.data.Hotel

class HotelRepository(private val hotelDao: BookingDao) {

    val getHotels:LiveData<List<Hotel>> = hotelDao.getHotels()
//    suspend fun getHotels(hotels: LiveData<List<Hotel>>) {
//        hotelDao.getHotels(hotels)
//    }

}
