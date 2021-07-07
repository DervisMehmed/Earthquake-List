package com.example.earthquakenotifier.Model

import java.io.Serializable

data class Earthquake(val date: String, val latitude: Double, val longitude: Double,
                      var depth: Double, var md: String, var ml: String, var mw: String,
                      val place: String, var status: String) : Serializable {

    override fun toString(): String {
        return "Date:\t\t$date\n\nLocation:\t\t$latitude - $longitude\nDepth:\t\t$depth" +
                "\nMagnitude:\t\t$md,\t$ml,\t$mw\nPlace:\t\t$place\nStatus:\t\t${status.replace('Ý', 'I')}"
    }
}
