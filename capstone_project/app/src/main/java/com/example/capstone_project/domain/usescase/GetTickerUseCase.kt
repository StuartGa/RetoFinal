package com.example.capstoneproject.domain.usescase

import android.util.Log
import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.data.local.entities.toDatabase
import com.example.capstoneproject.data.network.entities.response.TickerResponse
import com.example.capstoneproject.data.repository.CriptoRepository
import com.example.capstoneproject.domain.model.TickerDomain
import com.example.capstoneproject.domain.model.toDomain
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend operator fun invoke(book: String): Flow<Resource<TickerDomain>> =
        flow<Resource<TickerDomain>> {
            emit(Resource.loading())
            var result = TickerDomain("", "", "", "")
            try {
                Log.d("ticker 2", "si hay red")
                repository.getTickerApi(book)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { onSuccess: TickerResponse?, onError: Throwable? ->
                        onSuccess?.let {
                            result = it.ticker.toDomain()
                            Log.d("ticker 2", "result" + result)
                            println("----------------------------------------")
                            println("Rsponse ticker")
                            println("Domain" + result)
                        }
                        onError?.let {
                            println("error")
                        }
                    }
                emit(Resource.success(result))
                repository.insertTicker(result.toDatabase())
            } catch (e: Exception) {
                emit(Resource.error(e.message.toString()))
                println("Error" + e)
            }
        }.flowOn(Dispatchers.IO)
}
