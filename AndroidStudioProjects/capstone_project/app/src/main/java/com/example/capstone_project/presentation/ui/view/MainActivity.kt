package com.example.capstone_project.presentation.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_project.R
import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.entities.model.Ticker
import com.example.capstone_project.databinding.ActivityMainBinding
import com.example.capstone_project.presentation.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val tickerViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        CoroutineScope(Dispatchers.Main).launch {
            try {
                tickerViewModel.tickers.collect {
                    when (it) {
                        is Resource.Loading<Ticker> -> {}
                        is Resource.Success<Ticker> -> {
                        }

                        is Resource.Error<Ticker> -> {}
                    }
                }
            } catch (e: java.lang.Exception) {
                println(e)
            }
        } */
    }
}
