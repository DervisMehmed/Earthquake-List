package com.example.earthquakenotifier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquakenotifier.ui.mainscreen.MainScreen

class MainActivity : AppCompatActivity() {
    private lateinit var mainScreen: MainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainScreen = MainScreen()

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, mainScreen)
                .commitAllowingStateLoss()
        }
    }
}