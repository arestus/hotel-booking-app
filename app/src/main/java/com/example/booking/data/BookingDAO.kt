package com.example.booking.data

import androidx.room.*

@Dao
interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHotels(vararg hotels: Hotel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReservations(vararg reservation: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLoginHistory(vararg LoginHistory: LoginHistory)


}