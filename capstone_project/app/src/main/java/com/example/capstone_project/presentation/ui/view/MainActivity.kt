package com.example.capstoneproject.presentation.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.presentation.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.instance = this
    }
}
