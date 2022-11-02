package com.example.capstone_project.domain

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.local.entities.toDatabase
import com.example.capstone_project.data.network.entities.model.Ticker
import com.example.capstone_project.domain.model.TickerDomain
import com.example.capstone_project.presentation.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTickerUseCase {
    private val repository = CriptoRepository()

    suspend operator fun invoke(book: String): Flow<Resource<TickerDomain>> =
        flow<Resource<TickerDomain>> {
            emit(Resource.loading())
            try {
                val ticker = (if (Util.isNetworkEnabled()) repository.getTickerApi(book) else repository.getTicker())
                if(ticker != null){
                    repository.insertTicker(ticker.toDatabase())
                }
                emit(Resource.success(repository.getTickerApi(book)))
            } catch (e: Exception) {
                emit(Resource.error(e.message.toString()))
            }
        }
}
