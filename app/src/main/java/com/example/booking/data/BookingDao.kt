package com.example.booking.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHotels(vararg hotels: Hotel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReservations(vararg reservations: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLoginHistory(vararg loginHistory: LoginHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocalUsers(vararg localUsers: LocalUser)

    @Query("SELECT * FROM hotels")
    fun getHotels(): LiveData<List<Hotel>>

    @Query("DELETE FROM reservations")
    fun delReservation()

    @Query("SELECT * FROM reservations")
     fun getReservations(): LiveData<List<Reservation>>

    @Query("SELECT * FROM localUsers")
    fun getLocalUsers(): LiveData<List<LocalUser>>

    @Query("SELECT * FROM loginHistory")
     fun getLoginHistory(): LiveData<List<LoginHistory>>

    @Query("SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)")
    suspend fun getUser(): User

    @Query("SELECT * FROM reservations JOIN hotels ON reservations.hotelId=hotels.id ")
     fun fullReservation(): LiveData<List<Reservation>>

}