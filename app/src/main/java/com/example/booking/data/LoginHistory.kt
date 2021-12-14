package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loginHistory")
data class LoginHistory(
    @PrimaryKey
    val loginDateTimestamp: String
)

