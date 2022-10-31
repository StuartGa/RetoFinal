package com.example.capstone_project.domain

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.entities.response.OrderBookResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOrderBooksUseCase {
    private val repository = CriptoRepository()

    suspend operator fun invoke(book: String): Flow<Resource<OrderBookResponse>> =
        flow<Resource<OrderBookResponse>> {
            emit(Resource.loading<OrderBookResponse>())
            try {
                emit(Resource.Success(repository.getOrderBook(book)))
            } catch (e: java.lang.Exception) {
                e.message?.let { Resource.error<OrderBookResponse>(it) }?.let { emit(it) }
            }
        }
}
