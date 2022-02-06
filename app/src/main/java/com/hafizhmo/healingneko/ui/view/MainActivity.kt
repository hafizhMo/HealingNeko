package com.hafizhmo.healingneko.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.hafizhmo.healingneko.R
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var isSaved: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel.refreshFact()

        binding.refreshImage.setOnClickListener {
            mainViewModel.refreshFact()
        }

        binding.listImage.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        binding.saveImage.setOnClickListener {
            if (!isSaved) {
                mainViewModel.saveFact(binding.factText.text.toString())
            } else {
                mainViewModel.removeFact(binding.factText.toString())
            }
        }

        mainViewModel.factLiveData.observe(this) {
            if (it == null) {
                Toast.makeText(this, "Network call failed!", Toast.LENGTH_SHORT).show()
                return@observe
            }
            binding.factText.text = it.fact
        }

        mainViewModel.factIsSaved.observe(this) {
            isSaved = it
            if (it)
                binding.saveImage.setImageResource(R.drawable.ic_star_filled)
            else
                binding.saveImage.setImageResource(R.drawable.ic_star_outline)
        }

        mainViewModel.loading.observe(this) { loading ->
            binding.loading.isVisible = loading
        }
    }
}