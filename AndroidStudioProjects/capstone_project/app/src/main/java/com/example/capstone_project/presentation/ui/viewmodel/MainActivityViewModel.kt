package com.example.capstone_project.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.entities.model.Book
import com.example.capstone_project.data.entities.model.Ticker
import com.example.capstone_project.data.entities.response.OrderBookResponse
import com.example.capstone_project.domain.GetAvaibleBookUseCase
import com.example.capstone_project.domain.GetOrderBooksUseCase
import com.example.capstone_project.domain.GetTickerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val tickers: MutableStateFlow<Resource<Ticker>> = MutableStateFlow<Resource<Ticker>>(Resource.loading<Ticker>())

    private val _orderBooks = MutableStateFlow<Resource<OrderBookResponse>>(Resource.loading<OrderBookResponse>())
    val orderBooks: StateFlow<Resource<OrderBookResponse>> = _orderBooks

    private val _avaibleBooks = MutableStateFlow<Resource<List<Book>>>(Resource.loading<List<Book>>())
    val avaibleBooks: StateFlow<Resource<List<Book>>> = _avaibleBooks

    init {
        viewModelScope.launch {
            GetAvaibleBookUseCase().invoke().collect {
                println(it)
                _avaibleBooks.value = it
            }
        }
    }

    fun getOrderBooks(book: String) {
        viewModelScope.launch {
            GetOrderBooksUseCase().invoke(book)?.collect {
                println("response" + it)
                _orderBooks.value = it
            }
        }
    }

    fun getTicker(book: String) {
        viewModelScope.launch {
            GetTickerUseCase().invoke(book)?.collect() {
                tickers.value = it
            }
        }
    }
}
