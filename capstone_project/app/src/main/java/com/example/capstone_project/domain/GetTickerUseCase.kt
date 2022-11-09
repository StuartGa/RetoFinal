package com.example.capstone_project.domain

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.local.entities.toDatabase
import com.example.capstone_project.domain.model.TickerDomain
import com.example.capstone_project.presentation.util.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend operator fun invoke(book: String): Flow<Resource<TickerDomain>> =
        flow<Resource<TickerDomain>> {
            emit(Resource.loading())
            try {
                val ticker = (if (Util.isNetworkEnabled()) repository.getTickerApi(book) else repository.getTickerLocal())
                if (ticker != null) {
                    repository.insertTicker(ticker.toDatabase())
                }
                emit(Resource.success(repository.getTickerApi(book)))
            } catch (e: Exception) {
                emit(Resource.error(e.message.toString()))
            }
        }
}
