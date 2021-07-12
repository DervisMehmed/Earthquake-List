package com.example.earthquakenotifier.ui.mainscreen.quakeInfoScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText

class LocationInfoScreen : AppCompatActivity() {
    private lateinit var context: Context

    private lateinit var mBottomSheetLayout: LinearLayout
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var quakeMagnitude : TextView
    private lateinit var quakePlace : TextView
    private lateinit var quakeDate : TextView
    private lateinit var quakeDepth : TextView
    private lateinit var quakeCoor : TextView
    private lateinit var quakeMd : TextView
    private lateinit var quakeMl : TextView
    private lateinit var quakeMagPic: ImageView
    private lateinit var mapView: MapView
    lateinit var markerOptions : MarkerOptions
    lateinit var position : LatLng
    lateinit var marker : Marker

    private lateinit var obj : Earthquake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info_screen)
        initUI()
        context = this@LocationInfoScreen

        obj = intent.extras?.get("Object") as Earthquake
        loadUI(obj)
        this.title = obj.place

        position = LatLng(obj.latitude, obj.longitude)
        markerOptions = MarkerOptions().position(position)

        with(mapView) {
            onCreate(null)
            getMapAsync{
                MapsInitializer.initialize(applicationContext)
                setMapLocation(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadUI(obj: Earthquake) {
        quakeMagnitude.text = obj.ml
        quakePlace.text = obj.place
        quakeDate.text = obj.date
        quakeDepth.text = obj.depth.toString()
        quakeCoor.text = "${obj.latitude} N\n${obj.longitude} E"
        quakeMd.text = obj.md
        quakeMl.text = obj.ml
    }

    private fun initUI() {
        mBottomSheetLayout = findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout)
        quakeMagnitude = findViewById(R.id.textViewMagnitude)
        quakePlace = findViewById(R.id.textViewPlace)
        quakeDate = findViewById(R.id.textViewDate)
        quakeDepth = findViewById(R.id.textViewDepth)
        quakeCoor = findViewById(R.id.textViewLocCoor)
        quakeMd = findViewById(R.id.textViewLocMD)
        quakeMl = findViewById(R.id.textViewLocML)
        quakeMagPic = findViewById(R.id.imageViewLocMag)
        mapView = findViewById(R.id.mapView)
    }

    private fun setMapLocation(map: GoogleMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            marker = addMarker(markerOptions)
            setOnMapClickListener {
                if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }

            }
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