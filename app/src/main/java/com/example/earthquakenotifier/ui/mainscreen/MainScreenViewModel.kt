package com.example.earthquakenotifier.ui.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.Model.QuakeList
import com.example.earthquakenotifier.Model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel : ViewModel() {
    private lateinit var call : Call<QuakeList>
    var quakeList = MutableLiveData<List<Earthquake>>()
    private lateinit var actualList: List<Earthquake>

    fun loadLiveData() : MutableLiveData<List<Earthquake>> {
        this.call = getServiceCall()
        return loadData(call)
    }

    private fun getServiceCall() : Call<QuakeList> {
        return RetrofitClient.retroInterface.getData()
    }

    private fun loadData(call : Call<QuakeList>) :MutableLiveData<List<Earthquake>> {
        call.enqueue(object  : Callback<QuakeList> {

            override fun onResponse(call: Call<QuakeList>, response: Response<QuakeList>) {
                val body = response.body()
                if (body != null) {
                    quakeList.postValue(body.earthquakes)
                }
            }

            override fun onFailure(call: Call<QuakeList>, t: Throwable) {
                println("**** Failure ****\n$t")
            }
        })
        return quakeList
    }
}