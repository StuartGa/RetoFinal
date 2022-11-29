package com.example.capstone_project.data.Respository

import com.example.capstone_project.config.RoomHelper
import com.example.capstone_project.data.local.entities.AskEntity
import com.example.capstone_project.data.local.entities.BidsEntity
import com.example.capstone_project.data.local.entities.BookEntity
import com.example.capstone_project.data.local.entities.TickerEntity
import com.example.capstone_project.data.network.entities.model.Ticker
import com.example.capstone_project.data.network.service.AvaibleBookService
import com.example.capstone_project.data.network.service.OrderBookService
import com.example.capstone_project.data.network.service.TickerService
import com.example.capstone_project.domain.model.*

class CriptoRepository {
    private val tickerApi = TickerService()
    private val orderBookApi = OrderBookService()
    private val avaibleBookApi = AvaibleBookService()

    init {
        RoomHelper.provideDatabase()
    }
    suspend fun getTickerApi(book: String): TickerDomain {
        val response = tickerApi.getTicker(book)
        return response.ticker.toDomain()
    }

    suspend fun getAskApi(book: String): List<AskDomain> {
        val response = orderBookApi.getOrderBooks(book).payload.asks
        println("response order book" + response)
        return response.map { it.toDomain() }
    }
    suspend fun getBidsApi(book: String): List<BidDomain> {
        val response = orderBookApi.getOrderBooks(book).payload.bid
        println("response order book" + response)
        return response.map { it.toDomain() }
    }

    suspend fun getAvaibleBookApi(): List<BookDomain> {
        val response = avaibleBookApi.getOrderBooks().books.filter { it.book.contains("mxn") }
        return response.map { it.toDomain() }
    }

    suspend fun getAvaibleBookLocal(): List<BookDomain> {
        val resultdb: List<BookEntity> = RoomHelper.databaseInstance?.getBookDao()?.getAllBook() ?: emptyList()
        return resultdb.map { it.toDomain() }
    }

    suspend fun insertAvailableBooks(books: List<BookEntity>) {
        RoomHelper.databaseInstance?.getBookDao()?.insert(books.toTypedArray())
    }

    suspend fun getAllBidsLocal(): List<BidDomain> {
        val resultdb: List<BidsEntity> = RoomHelper.databaseInstance?.getBidDao()?.getAllBids() ?: emptyList()
        return resultdb.map { it.toDomain() }
    }
    suspend fun insertBidLocal(bid: List<BidsEntity>) {
        RoomHelper.databaseInstance?.getBidDao()?.insert(bid.toTypedArray())
    }

    suspend fun getAllAskLocal(): List<AskDomain> {
        val resultdb: List<AskEntity> = RoomHelper.databaseInstance?.getAskDao()?.getAllAsks() ?: emptyList()
        return resultdb.map { it.toDomain() }
    }
    suspend fun insertAsk(ask: List<AskEntity>) {
        RoomHelper.databaseInstance?.getAskDao()?.insert(ask.toTypedArray())
    }

    suspend fun insertTicker(ticker: TickerEntity) {
        RoomHelper.databaseInstance?.getTickerDao()?.insert(ticker)
    }

    fun getTicker(): TickerDomain {
        val resultdb: Ticker? = RoomHelper.databaseInstance?.getTickerDao()?.selectTicker()
        return resultdb?.let {
            it.toDomain()
        } ?: TickerDomain("", "", "", "")
    }
}
