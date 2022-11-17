package com.example.capstoneproject.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.data.local.entities.toDatabase
import com.example.capstoneproject.data.network.entities.response.TickerResponse
import com.example.capstoneproject.data.repository.CriptoRepository
import com.example.capstoneproject.domain.model.AskDomain
import com.example.capstoneproject.domain.model.BidDomain
import com.example.capstoneproject.domain.model.TickerDomain
import com.example.capstoneproject.domain.model.toDomain
import com.example.capstoneproject.domain.usescase.GetAskUseCase
import com.example.capstoneproject.domain.usescase.GetBidsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CriptoDetailViewModel@Inject constructor(
    private val getBidsUseCase: GetBidsUseCase,
    private val getAskUseCase: GetAskUseCase,
    private val repository: CriptoRepository
) : ViewModel() {

    lateinit var bookFragment: String

    private var _tickerlive = MutableLiveData<Resource<TickerDomain>>()
    val tickerlive: LiveData<Resource<TickerDomain>> = _tickerlive

    private val _asks = MutableStateFlow<Resource<List<AskDomain>>>(Resource.loading<List<AskDomain>>())
    val asks: StateFlow<Resource<List<AskDomain>>> = _asks

    private val _bids = MutableStateFlow<Resource<List<BidDomain>>>(Resource.loading<List<BidDomain>>())
    val bids: StateFlow<Resource<List<BidDomain>>> = _bids

    fun getBook(book: String) {
        bookFragment = book
    }
    fun getBids(book: String) {
        viewModelScope.launch {
            getBidsUseCase.invoke(book).collect {
                println("response" + it)
                _bids.value = it
            }
        }
    }

    fun getAsks(book: String) {
        viewModelScope.launch {
            getAskUseCase.invoke(book).collect {
                println("response" + it)
                _asks.value = it
            }
        }
    }

    fun getTicker(book: String) {
        try {
            Log.d("ticker 2", "si hay red")
            repository.getTickerApi(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess: TickerResponse?, onError: Throwable? ->
                    onSuccess?.let {
                        val result = it.ticker.toDomain()
                        _tickerlive.postValue(Resource.success(result))
                        viewModelScope.launch { repository.insertTicker(result.toDatabase()) }
                    }
                    onError?.let {
                        _tickerlive.postValue(Resource.error("Ha ocurrido un error en la peticion"))
                    }
                }
        } catch (e: Exception) {
            _tickerlive.postValue(Resource.error(e.message.toString()))
        }
    }
}
