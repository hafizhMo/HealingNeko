package com.hafizhmo.healingneko.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.healingneko.databinding.ActivityDetailBinding
import com.hafizhmo.healingneko.ui.adapter.FactAdapter
import com.hafizhmo.healingneko.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerFact.layoutManager = LinearLayoutManager(this)

        detailViewModel.loadFactFromDatabase()
        detailViewModel.factLiveData.observe(this){
            binding.recyclerFact.adapter = FactAdapter(it!!){ fact ->
                detailViewModel.removeFact(fact)
            }
        }
    }
}