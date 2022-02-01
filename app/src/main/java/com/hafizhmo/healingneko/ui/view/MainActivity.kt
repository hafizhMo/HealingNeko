package com.hafizhmo.healingneko.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hafizhmo.healingneko.data.local.Fact
import com.hafizhmo.healingneko.data.local.FactDatabase
import com.hafizhmo.healingneko.data.remote.FactResponse
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.ui.viewmodel.MainViewModel
import com.hafizhmo.healingneko.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var db: FactDatabase

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        db = FactDatabase.getDatabase(this)

        loadFact()

        binding.refreshImage.setOnClickListener {
            loadFact()
        }

        binding.saveImage.setOnClickListener {
            saveFact()
        }
    }

    private fun loadFact() {
        mainViewModel.fetchData()!!.observe(this, {
            binding.factText.text = it.fact
        })
    }

    private fun saveFact() {
        val savedFact = Fact(binding.factText.text.toString())
        executorService.execute {
            db.factDao().insertFact(savedFact)

            val facts: List<Fact> = db.factDao().getAllFact()
            for (fact in facts)
                Log.d("ROOM", fact.fact)
        }
    }
}