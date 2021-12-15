package com.example.booking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        User::class,
        Hotel::class,
        Reservation::class,
        LoginHistory::class,
        LocalUser::class
    ],
    version = 1)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao

    companion object{
        @Volatile
        private var INSTANCE: BookingDatabase? = null

        fun getInstance(context: Context): BookingDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookingDatabase::class.java,
                    "BookingDB"
                ).build().also {
                    INSTANCE = it
                }

            }
        }
    }
}