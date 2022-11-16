package com.example.capstone_project.domain.usescase

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.local.entities.toDatabase
import com.example.capstone_project.data.repository.CriptoRepository
import com.example.capstone_project.domain.model.BookDomain
import com.example.capstone_project.presentation.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAvaibleBookUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend fun invoke(): Flow<Resource<List<BookDomain>>> =
        flow<Resource<List<BookDomain>>> {
            emit(Resource.loading())
            try {
                val book = (if (Util.isNetworkEnabled()) repository.getAvaibleBookApi() else repository.getAvaibleBookLocal())
                if (book.isNotEmpty()) {
                    repository.insertAvailableBooks(book.map { it.toDatabase() })
                }
                emit(Resource.success(book))
            } catch (e: java.lang.Exception) {
                emit(Resource.error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}
