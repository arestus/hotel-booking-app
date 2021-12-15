package com.example.booking.data

import androidx.room.*

@Dao
interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHotels(vararg hotels: Hotel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReservations(vararg reservations: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLoginHistory(vararg loginHistory: LoginHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocalUsers(vararg localUsers: LocalUser)

    @Query("SELECT * FROM hotels")
    suspend fun getHotels(): List<Hotel>
}