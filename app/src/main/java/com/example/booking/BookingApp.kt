package com.example.booking

import android.app.Application
import android.util.Log

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import com.fasterxml.jackson.databind.DeserializationFeature
import retrofit.GsonConverterFactory
import java.io.IOException

class BookingApp : Application() {
    lateinit var httpApiService: HttpApiService
    lateinit var session: SessionManager

    override fun onCreate() {
        super.onCreate()
        session = SessionManager(applicationContext)
        val token = session.getToken()
        httpApiService =
            if (token.isNullOrEmpty()) initHttpApiService()
            else initHttpApiService(token)
    }

    private fun initHttpApiService(authToken: String? = null): HttpApiService {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(newRequest)
        }.build()
        Log.d("HttpString", "$authToken")
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://android-kanini-course.cloud/hotelBooking/")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()

        Log.d("HttpString", "$retrofit")
        return retrofit.create(HttpApiService::class.java)

    }
}