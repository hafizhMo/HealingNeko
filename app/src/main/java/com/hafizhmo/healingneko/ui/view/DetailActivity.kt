package com.hafizhmo.healingneko.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.healingneko.data.local.entity.FactEntity
import com.hafizhmo.healingneko.databinding.ActivityDetailBinding
import com.hafizhmo.healingneko.ui.adapter.FactAdapter
import com.hafizhmo.healingneko.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var factAdapter: FactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        detailViewModel.loadFactFromDatabase()
        factAdapter = FactAdapter { fact ->
            detailViewModel.removeFact(fact)
            detailViewModel.loadFactFromDatabase()
        }
        binding.recyclerFact.layoutManager = LinearLayoutManager(this)
        binding.recyclerFact.adapter = factAdapter

        detailViewModel.factLiveData.observe(this) {
            loadFact(it!!)
        }

        binding.imageClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadFact(list: List<FactEntity>) {
        list.let {
            factAdapter.clearFacts()
            factAdapter.fillFacts(list)
        }
    }
}