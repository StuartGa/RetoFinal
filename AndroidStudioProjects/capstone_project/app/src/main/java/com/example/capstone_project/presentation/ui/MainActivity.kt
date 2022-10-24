package com.example.capstone_project.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_project.databinding.ActivityMainBinding
import com.example.capstone_project.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivity: ActivityMainBinding
    val tickerViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mainActivity.root)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                tickerViewModel.tickers.collect {
                    mainActivity.apply {
                        this.book.text = it.book
                        this.low.text = it.low
                        this.volume.text = it.volume
                    }
                }
            } catch (e: java.lang.Exception) {
                println(e)
            }
        }
    }
}
