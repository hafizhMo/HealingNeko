package com.hafizhmo.healingneko

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.hafizhmo.healingneko.data.local.Fact
import com.hafizhmo.healingneko.data.local.FactDatabase
import com.hafizhmo.healingneko.data.remote.FactResponse
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.utils.ApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        db = Room.databaseBuilder(
            applicationContext,
            FactDatabase::class.java,
            "fact-db"
        ).build()

        loadFact()

        binding.refreshImage.setOnClickListener {
            loadFact()
        }

        binding.saveImage.setOnClickListener {
            GlobalScope.launch {
                saveFact()
            }
        }
    }

    private fun saveFact() {
        val savedFact = Fact(binding.factText.text.toString())
        db.factDao().insertFact(savedFact)

        val facts : List<Fact> = db.factDao().getAllFact()
        for (fact in facts)
            Log.d("ROOM", fact.fact)
    }

    private fun loadFact() {
        val call = ApiClient.retrofitFactService.getFact()

        call.enqueue(object : Callback<FactResponse> {
            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                val result = response.body()!!
                binding.factText.text = result.fact
            }

            override fun onFailure(call: Call<FactResponse>, t: Throwable) {
                binding.factText.text = t.toString()
            }
        })
    }
}