package com.example.capstone_project.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.data.Resource
import com.example.capstone_project.domain.model.AskDomain
import com.example.capstone_project.domain.model.BidDomain
import com.example.capstone_project.domain.model.TickerDomain
import com.example.capstone_project.domain.usescase.GetAskUseCase
import com.example.capstone_project.domain.usescase.GetBidsUseCase
import com.example.capstone_project.domain.usescase.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CriptoDetailViewModel@Inject constructor(
    private val getBidsUseCase: GetBidsUseCase,
    private val getAskUseCase: GetAskUseCase,
    private val getTickerUseCase: GetTickerUseCase
) : ViewModel() {

    lateinit var bookFragment: String

    val tickers: MutableStateFlow<Resource<TickerDomain>> = MutableStateFlow<Resource<TickerDomain>>(
        Resource.loading<TickerDomain>()
    )

    private val _asks = MutableStateFlow<Resource<List<AskDomain>>>(Resource.loading<List<AskDomain>>())
    val asks: StateFlow<Resource<List<AskDomain>>> = _asks

    private val _bids = MutableStateFlow<Resource<List<BidDomain>>>(Resource.loading<List<BidDomain>>())
    val bids: StateFlow<Resource<List<BidDomain>>> = _bids

    fun getBook(book: String) {
        bookFragment = book
    }
    fun getBids(book: String) {
        viewModelScope.launch {
            getBidsUseCase.invoke(book)?.collect {
                println("response" + it)
                _bids.value = it
            }
        }
    }

    fun getAsks(book: String) {
        viewModelScope.launch {
            getAskUseCase.invoke(book)?.collect {
                println("response" + it)
                _asks.value = it
            }
        }
    }

    fun getTicker(book: String) {
        viewModelScope.launch {
            getTickerUseCase.invoke(book)?.collect() {
                tickers.value = it
            }
        }
    }
}
