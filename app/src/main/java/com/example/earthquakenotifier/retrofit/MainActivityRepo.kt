package com.example.earthquakenotifier.retrofit

import androidx.lifecycle.MutableLiveData
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.Model.QuakeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Handler

object MainActivityRepo {
    private var quakeList = MutableLiveData<List<Earthquake>>()

    fun getServiceCall() : Call<QuakeList> {
        val call = RetrofitClient.retroInterface.getData()
        return call
    }

    fun loadData(call : Call<QuakeList>) : MutableLiveData<List<Earthquake>> {
        call?.enqueue(object  : Callback<QuakeList> {
            override fun onResponse(call: Call<QuakeList>, response: Response<QuakeList>) {
                val body = response.body()
                if (body != null)
                {
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