package com.example.capstone_project.domain

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.entities.model.Ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTickerUseCase {
    private val repository = CriptoRepository()

    suspend operator fun invoke(book: String): Flow<Resource<Ticker>>? =
        flow<Resource<Ticker>> {
            emit(Resource.loading())
            try {
                emit(Resource.success(repository.getTicker(book).ticker))
            } catch (e: Exception) {
                emit(Resource.error(e.message.toString()))
            }
        }
}
