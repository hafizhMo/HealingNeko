package com.hafizhmo.healingneko.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hafizhmo.healingneko.databinding.ActivityMainBinding
import com.hafizhmo.healingneko.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel.refreshFact()

        binding.refreshImage.setOnClickListener {
            mainViewModel.refreshFact()
        }

        binding.saveImage.setOnClickListener {
            //todo save fact
        }

        mainViewModel.factLiveData.observe(this){
            if(it == null){
                Toast.makeText(this, "Network call failed!", Toast.LENGTH_SHORT).show()
                return@observe
            }
            binding.factText.text = it.fact
        }
    }
}