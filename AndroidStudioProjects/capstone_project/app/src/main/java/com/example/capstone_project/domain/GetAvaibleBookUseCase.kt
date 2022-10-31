package com.example.capstone_project.domain

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.entities.model.Book
import com.example.capstone_project.data.entities.response.AvaibleBookResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAvaibleBookUseCase {
    val repository = CriptoRepository()

    suspend fun invoke(): Flow<Resource<List<Book>>> =
        flow<Resource<List<Book>>> {
            emit(Resource.loading())
            try {
                emit(Resource.success(repository.getAvaibleBook()))
            } catch (e: java.lang.Exception) {
                emit(Resource.error(e.message.toString()))
            }
        }
}
