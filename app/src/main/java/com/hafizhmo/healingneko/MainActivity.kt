package com.hafizhmo.healingneko

import android.os.Bundle
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.hafizhmo.healingneko.data.Fact
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var anim: RotateAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        loadFact()

        binding.refreshImage.setOnClickListener {
            loadFact()
        }
    }

    private fun loadFact() {
        val call = ApiClient.retrofitFactService.getFact()

        call.enqueue(object : Callback<Fact> {
            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                val result = response.body()!!
                binding.factText.text = result.fact
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                binding.factText.text = t.toString()
            }
        })
    }
}