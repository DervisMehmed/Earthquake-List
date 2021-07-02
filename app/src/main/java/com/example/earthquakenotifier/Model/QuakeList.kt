package com.example.earthquakenotifier.Model

import com.google.gson.annotations.SerializedName

data class QuakeList(@SerializedName("data") var earthquakes : List<Earthquake>)
