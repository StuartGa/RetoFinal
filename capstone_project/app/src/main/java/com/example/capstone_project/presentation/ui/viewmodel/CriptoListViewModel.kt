package com.example.capstoneproject.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.domain.model.BookDomain
import com.example.capstoneproject.domain.usescase.GetAvaibleBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getavaibleBookUseCase: GetAvaibleBookUseCase

) : ViewModel() {

    private val _avaibleBooks = MutableStateFlow<Resource<List<BookDomain>>>(Resource.loading<List<BookDomain>>())
    val avaibleBooks: StateFlow<Resource<List<BookDomain>>> = _avaibleBooks

    init {
        viewModelScope.launch {
            getavaibleBookUseCase.invoke().collect {
                println(it)
                _avaibleBooks.value = it
            }
        }
    }
}
