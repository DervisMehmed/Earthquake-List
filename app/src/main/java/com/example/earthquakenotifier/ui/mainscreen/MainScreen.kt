package com.example.earthquakenotifier.ui.mainscreen

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.earthquakenotifier.ui.mainscreen.quakeInfoScreen.LocationInfoScreen
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import java.io.Serializable

class MainScreen : Fragment() {
    private var quakeList = MutableLiveData<List<Earthquake>>()
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var listViewAdapter: ArrayAdapter<*>
    private lateinit var quakeListView: ListView
    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.main_screen_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        quakeListView = root.findViewById(R.id.entryListView)
        quakeList = viewModel.loadLiveData()

        Handler().postDelayed({
            viewModel.loadLiveData()
        }, 60000)

        quakeList.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let {
                    it1 -> ArrayAdapter(it1.applicationContext, android.R.layout.simple_list_item_1, it) }!!
            quakeListView.adapter = listViewAdapter
        })

        quakeListView.setOnItemClickListener { parent, view, position, id ->
            // The item that was clicked
            val element = parent.adapter.getItem(position)
            val intent = Intent(parent.context, LocationInfoScreen::class.java)
            intent.putExtra("Object", element as Serializable)
            startActivity(intent)
        }
        return root
    }
}