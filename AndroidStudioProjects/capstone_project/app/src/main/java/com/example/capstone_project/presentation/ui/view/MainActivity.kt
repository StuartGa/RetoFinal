package com.example.capstone_project.presentation.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_project.R
import com.example.capstone_project.config.RoomHelper
import com.example.capstone_project.databinding.ActivityMainBinding
import com.example.capstone_project.presentation.Util
import com.example.capstone_project.presentation.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val tickerViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.getInstance(this)
    }
}
