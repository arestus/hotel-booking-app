package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: Int = 0,
    val email: String = "",
    val password: String = "",
    val token: String = "",
    val memberSince: String = ""
)
