package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val id: Int = 0,
    val email: String = "",
    var password: String? = null,
    val token: String = "",
    val memberSince: Long = 0
)
