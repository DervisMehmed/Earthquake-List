package com.example.earthquakenotifier.ui.mainscreen.quakeInfoScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LocationInfoScreen : AppCompatActivity() {
    private lateinit var context: Context

    private lateinit var quakeInfo : TextView
    private lateinit var mapView: MapView
    lateinit var markerOptions : MarkerOptions
    lateinit var position : LatLng
    lateinit var marker : Marker

    private lateinit var obj : Earthquake
    private lateinit var activityTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info_screen)
        quakeInfo = findViewById(R.id.earthquakeInfo)
        mapView = findViewById(R.id.mapView)

        context = this@LocationInfoScreen
        obj = intent.extras?.get("Object") as Earthquake
        activityTitle = obj.place
        this.title = activityTitle

        position = LatLng(obj.latitude, obj.longitude)
        markerOptions = MarkerOptions().position(position)
        quakeInfo.text = obj.toString()

        with(mapView) {
            onCreate(null)
            getMapAsync{
                MapsInitializer.initialize(applicationContext)
                setMapLocation(it)
            }
        }
    }

    private fun setMapLocation(map : GoogleMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            marker = addMarker(markerOptions)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}