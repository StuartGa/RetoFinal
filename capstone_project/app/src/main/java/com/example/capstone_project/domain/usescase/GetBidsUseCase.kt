package com.example.capstoneproject.domain.usescase

import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.data.local.entities.toDatabase
import com.example.capstoneproject.data.repository.CriptoRepository
import com.example.capstoneproject.domain.model.BidDomain
import com.example.capstoneproject.presentation.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetBidsUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend operator fun invoke(book: String): Flow<Resource<List<BidDomain>>> =
        flow<Resource<List<BidDomain>>> {
            emit(Resource.loading())
            try {
                val bid = if (Util.isNetworkEnabled()) repository.getBidsApi(book) else repository.getAllBidsLocal()
                if (bid.isNotEmpty()) {
                    repository.insertBidLocal(bid.map { it.toDatabase() })
                }
                emit(Resource.Success(bid))
            } catch (e: Exception) {
                e.message?.let { Resource.error<String>("Error en la peticion") }
            }
        }.flowOn(Dispatchers.IO)
}
