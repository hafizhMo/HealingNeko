package com.hafizhmo.healingneko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hafizhmo.healingneko.data.Fact
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFact()
    }

    private fun loadFact(){
        val call = ApiClient.retrofitFactService.getFact()

        call.enqueue(object : Callback<Fact> {
            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                binding.factText.text = response.body()!!.fact
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                binding.factText.text = t.toString()
            }
        })
    }
}