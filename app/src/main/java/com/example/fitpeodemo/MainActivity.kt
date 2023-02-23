package com.example.fitpeodemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitpeodemo.databinding.ActivityMainBinding
import com.example.fitpeodemo.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        lifecycleScope.launch {
                if (homeViewModel.isOnline.first()) {
                    Toast.makeText(this@MainActivity, "Internet Connection is success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
                }

        }
    }
}