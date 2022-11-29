package com.example.capstone_project.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.network.entities.model.Book
import com.example.capstone_project.data.network.entities.model.Ticker
import com.example.capstone_project.data.network.entities.response.OrderBookResponse
import com.example.capstone_project.domain.*
import com.example.capstone_project.domain.model.AskDomain
import com.example.capstone_project.domain.model.BidDomain
import com.example.capstone_project.domain.model.BookDomain
import com.example.capstone_project.domain.model.TickerDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val tickers: MutableStateFlow<Resource<TickerDomain>> = MutableStateFlow<Resource<TickerDomain>>(Resource.loading<TickerDomain>())

    private val _asks = MutableStateFlow<Resource<List<AskDomain>>>(Resource.loading<List<AskDomain>>())
    val asks: StateFlow<Resource<List<AskDomain>>> = _asks

    private val _bids = MutableStateFlow<Resource<List<BidDomain>>>(Resource.loading<List<BidDomain>>())
    val bids: StateFlow<Resource<List<BidDomain>>> = _bids

    private val _avaibleBooks = MutableStateFlow<Resource<List<BookDomain>>>(Resource.loading<List<BookDomain>>())
    val avaibleBooks: StateFlow<Resource<List<BookDomain>>> = _avaibleBooks

    init {
        viewModelScope.launch {
            GetAvaibleBookUseCase().invoke().collect {
                println(it)
                _avaibleBooks.value = it
            }
        }
    }

    fun getBids(book: String) {
        viewModelScope.launch {
            GetBidsUseCase().invoke(book)?.collect {
                println("response" + it)
                _bids.value = it
            }
        }
    }

    fun getAsks(book: String) {
        viewModelScope.launch {
            GetAskUseCase().invoke(book)?.collect {
                println("response" + it)
                _asks.value = it
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
