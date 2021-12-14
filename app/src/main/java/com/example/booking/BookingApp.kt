package com.example.booking

import android.app.Application

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request


class BookingApp : Application() {
    public lateinit var httpApiService: HttpApiService

    override fun onCreate() {
        super.onCreate()

        httpApiService = initHttpApiService()
    }

    private fun initHttpApiService(authToken: String? = null): HttpApiService {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(newRequest)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/hotelBooking/")
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()
        return retrofit.create(HttpApiService::class.java)

    }
}