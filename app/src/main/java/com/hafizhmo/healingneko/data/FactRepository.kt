package com.hafizhmo.healingneko.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hafizhmo.healingneko.data.remote.FactResponse
import com.hafizhmo.healingneko.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactRepository {

    private val factResponse = MutableLiveData<FactResponse>()

    fun getFactResponse(): MutableLiveData<FactResponse>{

        val call = ApiClient.retrofitFactService.getFact()

        call.enqueue(object : Callback<FactResponse>{
            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                factResponse.value = response.body()!!
            }

            override fun onFailure(call: Call<FactResponse>, t: Throwable) {
                Log.d("FactRepository", "Error: $t")
            }
        })

        return factResponse
    }
}