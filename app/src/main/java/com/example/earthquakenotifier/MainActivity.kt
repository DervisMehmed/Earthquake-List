package com.example.earthquakenotifier

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.ViewModel.MainActivityViewModel
import java.io.Serializable
import androidx.lifecycle.Observer as Observer1

class MainActivity : AppCompatActivity()
{
    private lateinit var context: Context
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var quakeListView : ListView
    private lateinit var listViewAdapter : ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quakeListView = findViewById(R.id.entryListView)
        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.loadLiveData()

        Handler().postDelayed({
            mainActivityViewModel.loadLiveData()
                              }, 60000)

        mainActivityViewModel.quakeListLiveData?.observe(this,
                {
                    listViewAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
                    quakeListView.adapter = listViewAdapter
                })

        quakeListView.setOnItemClickListener { parent, view, position, id ->
            // The item that was clicked
            val element = parent.adapter.getItem(position)
            val intent = Intent(this, LocationInfoScreen::class.java)

            intent.putExtra("Object", element as Serializable)
            startActivity(intent)
        }
    }
}