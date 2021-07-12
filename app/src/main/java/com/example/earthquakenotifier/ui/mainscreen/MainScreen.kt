package com.example.earthquakenotifier.ui.mainscreen

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.earthquakenotifier.ui.mainscreen.quakeInfoScreen.LocationInfoScreen
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import com.google.android.material.textfield.TextInputEditText
import java.io.Serializable
import java.util.*

class MainScreen : Fragment() {
    private var quakeList = MutableLiveData<List<Earthquake>>()
    private lateinit var actualList: MutableLiveData<List<Earthquake>>
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var listViewAdapter: CardViewAdapter
    private lateinit var quakeListView: ListView
    private lateinit var inputText : TextInputEditText
    private lateinit var root: View

    private var delaymillis: Long = 60000

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.main_screen_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        quakeListView = root.findViewById(R.id.entryListView)
        inputText = root.findViewById(R.id.TextInputText)
        actualList = viewModel.loadLiveData()
        quakeList = viewModel.loadLiveData()

        Handler().postDelayed({
            viewModel.loadLiveData()
        }, delaymillis)

        quakeList.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let {
                    it1 -> CardViewAdapter(it1.applicationContext, it)}!!
            quakeListView.adapter = listViewAdapter
        })

        inputText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isEmpty()){
                    //viewModel.loadLiveData()
                    quakeList.postValue(actualList.value)
                }
                else{
                    val searchtext: String = text.toString().toUpperCase(Locale.ROOT)
                    quakeList.value =
                        quakeList.value?.filter { e -> e.place.contains(searchtext)}!!
                }
                Log.d("actualLIST", "size : " + actualList.value!!.size)
            }
        }

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