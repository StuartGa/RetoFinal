package com.example.capstone_project.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.data.entities.model.Ticker
import com.example.capstone_project.domain.GetTickerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val tickers: MutableStateFlow<Ticker> = MutableStateFlow(Ticker("", "", ""))

    init {
        viewModelScope.launch {
            val result = GetTickerUseCase().invoke()
            if (result?.low != null) {
                tickers.value = result
            } else {
                println("resultado" + tickers.value)
            }
        }
    }
}
