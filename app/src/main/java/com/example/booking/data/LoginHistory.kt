package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "loginHistory")
data class LoginHistory(
    @PrimaryKey
    @JsonProperty("loginDateTimestamp") val loginDateTimestamp: String = ""
)

