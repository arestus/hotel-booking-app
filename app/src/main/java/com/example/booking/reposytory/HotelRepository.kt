package com.example.booking.reposytory

import androidx.lifecycle.LiveData
import com.example.booking.data.*

class HotelRepository(private val hotelDao: BookingDao) {

    val getHotels: LiveData<List<Hotel>> = hotelDao.getHotels()
    val getLocalUsers: LiveData<List<LocalUser>> = hotelDao.getLocalUsers()
    val getLoginHistory: LiveData<List<LoginHistory>> = hotelDao.getLoginHistory()
    val getReservations: LiveData<List<Reservation>> = hotelDao.fullReservation()
}
