package com.example.capstoneproject.domain.usescase

import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.data.local.entities.toDatabase
import com.example.capstoneproject.data.repository.CriptoRepository
import com.example.capstoneproject.domain.model.BookDomain
import com.example.capstoneproject.presentation.util.Util
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
