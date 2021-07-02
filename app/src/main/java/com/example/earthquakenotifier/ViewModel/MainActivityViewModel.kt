package com.example.earthquakenotifier.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.Model.QuakeList
import com.example.earthquakenotifier.retrofit.MainActivityRepo
import retrofit2.Call

class MainActivityViewModel : ViewModel() {
    var quakeListLiveData : MutableLiveData<List<Earthquake>>? = null
    private lateinit var call : Call<QuakeList>

    fun loadLiveData()
    {
        this.call = MainActivityRepo.getServiceCall()

        quakeListLiveData = MainActivityRepo.loadData(call)
    }
}