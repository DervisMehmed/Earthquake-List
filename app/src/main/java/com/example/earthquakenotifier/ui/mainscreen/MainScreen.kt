package com.example.earthquakenotifier.ui.mainscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainScreen : Fragment() {
    private var quakeList = MutableLiveData<List<Earthquake>>()
    private lateinit var actualList: MutableLiveData<List<Earthquake>>
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var quakeListView: RecyclerView
    private lateinit var quakeListAdapter: CustomRecyclerViewAdapter
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
        linearLayoutManager = LinearLayoutManager(this.context)
        quakeListView.layoutManager = linearLayoutManager
        actualList = viewModel.loadLiveData()
        quakeList = viewModel.loadLiveData()

        Handler().postDelayed({
            viewModel.loadLiveData()
        }, delaymillis)

        quakeList.observe(viewLifecycleOwner, Observer {
            quakeListAdapter = quakeList.value?.let { CustomRecyclerViewAdapter(this.activity, it) }!!
            quakeListView.adapter = quakeListAdapter
        })

        inputText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isEmpty()){
                    viewModel.loadLiveData()
                }
                else{
                    val searchtext: String = text.toString().toUpperCase(Locale.ROOT)
                    quakeList.value =
                        quakeList.value?.filter { e -> e.place.contains(searchtext)}!!
                }
            }
        }
        return root
    }
}