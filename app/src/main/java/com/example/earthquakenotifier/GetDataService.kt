package com.example.earthquakenotifier

import com.example.earthquakenotifier.Model.QuakeList
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService
{
    @GET("/st/intern/")
    fun getData(): Call<QuakeList>
}