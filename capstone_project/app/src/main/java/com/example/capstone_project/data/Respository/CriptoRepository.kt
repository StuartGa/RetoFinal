package com.example.capstone_project.data.Respository

import com.example.capstone_project.data.local.DAO.AskDao
import com.example.capstone_project.data.local.DAO.BidDao
import com.example.capstone_project.data.local.DAO.BookDao
import com.example.capstone_project.data.local.DAO.TickerDao
import com.example.capstone_project.data.local.entities.AskEntity
import com.example.capstone_project.data.local.entities.BidsEntity
import com.example.capstone_project.data.local.entities.BookEntity
import com.example.capstone_project.data.local.entities.TickerEntity
import com.example.capstone_project.data.network.entities.model.Ticker
import com.example.capstone_project.data.network.service.CryptoService
import com.example.capstone_project.domain.model.*
import javax.inject.Inject

class CriptoRepository @Inject constructor(
    private val api: CryptoService,
    private val bookDao: BookDao,
    private val bidDao: BidDao,
    private val askDao: AskDao,
    private val tickerDao: TickerDao
) {

    suspend fun getTickerApi(book: String): TickerDomain {
        val response = api.getTicker(book)
        return response.ticker.toDomain()
    }

    suspend fun getAskApi(book: String): List<AskDomain> {
        val response = api.getOrderBooks(book).payload.asks
        println("response order book" + response)
        return response.map { it.toDomain() }
    }
    suspend fun getBidsApi(book: String): List<BidDomain> {
        val response = api.getOrderBooks(book).payload.bid
        println("response order book" + response)
        return response.map { it.toDomain() }
    }

    suspend fun getAvaibleBookApi(): List<BookDomain> {
        val response = api.getOrderBooks().books.filter { it.book.contains("mxn") }
        return response.map { it.toDomain() }
    }

    suspend fun getAvaibleBookLocal(): List<BookDomain> {
        val resultdb: List<BookEntity> = bookDao.getAllBook()
        return resultdb.map { it.toDomain() }
    }

    suspend fun insertAvailableBooks(books: List<BookEntity>) {
        bookDao.insert(books.toTypedArray())
    }

    suspend fun getAllBidsLocal(): List<BidDomain> {
        val resultdb: List<BidsEntity> = bidDao.getAllBids()
        return resultdb.map { it.toDomain() }
    }
    suspend fun insertBidLocal(bid: List<BidsEntity>) {
        bidDao.insert(bid.toTypedArray())
    }

    suspend fun getAllAskLocal(): List<AskDomain> {
        val resultdb: List<AskEntity> = askDao.getAllAsks()
        return resultdb.map { it.toDomain() }
    }
    suspend fun insertAsk(ask: List<AskEntity>) {
        askDao.insert(ask.toTypedArray())
    }

    suspend fun insertTicker(ticker: TickerEntity) {
        tickerDao.insert(ticker)
    }

    fun getTickerLocal(): TickerDomain {
        val resultdb: Ticker = tickerDao.selectTicker()
        return resultdb.toDomain()
    }
}
