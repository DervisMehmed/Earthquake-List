package com.example.earthquakenotifier.Model

import java.io.Serializable

data class Earthquake(val date: String, val latitude: Double, val longitude: Double,
                      var depth: Double, var md: String, var ml: String, var mw: String,
                      val place: String, var status: String) : Serializable
{

    override fun toString(): String {
        return "Date: $date\nLocation: $latitude - $longitude\nDepth: $depth" +
                "\nMagnitude: $md , $ml , $mw\nPlace: $place\nStatus: ${status.replace('Ý', 'I')}\n"
    }
}
