package com.example.earthquakenotifier.retrofit

import com.example.earthquakenotifier.GetDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val SERVICE_URL = "https://apps.furkansandal.com/"

    val retrofitInstance: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val retroInterface: GetDataService by lazy {
        retrofitInstance
            .build()
            .create(GetDataService::class.java)
    }
}
