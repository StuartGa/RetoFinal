package com.example.capstone_project.domain.usescase

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.local.entities.toDataBase
import com.example.capstone_project.data.repository.CriptoRepository
import com.example.capstone_project.domain.model.AskDomain
import com.example.capstone_project.presentation.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAskUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend operator fun invoke(book: String): Flow<Resource<List<AskDomain>>> =
        flow<Resource<List<AskDomain>>> {
            emit(Resource.loading())
            try {
                val ask = (if (Util.isNetworkEnabled()) repository.getAskApi(book) else repository.getAllAskLocal())
                if (ask.isNotEmpty()) {
                    repository.insertAsk(ask.map { it.toDataBase() })
                }
                emit(Resource.Success(ask))
            } catch (e: java.lang.Exception) {
                e.message?.let { Resource.error<String>("No se pudo realizar la peticion") }
            }
        }.flowOn(Dispatchers.IO)
}
