package com.example.capstone_project.data.Respository

import com.example.capstone_project.data.TickerProvider
import com.example.capstone_project.data.entities.model.Book
import com.example.capstone_project.data.entities.response.OrderBookResponse
import com.example.capstone_project.data.entities.response.TickerResponse
import com.example.capstone_project.data.network.service.AvaibleBookService
import com.example.capstone_project.data.network.service.OrderBookService
import com.example.capstone_project.data.network.service.TickerService

class CriptoRepository {
    private val tickerApi = TickerService()
    private val orderBookApi = OrderBookService()
    private val avaibleBookApi = AvaibleBookService()

    suspend fun getTicker(book: String): TickerResponse {
        val response = tickerApi.getTicker(book)
        TickerProvider.ticker = response // Local
        return response
    }

    suspend fun getOrderBook(book: String): OrderBookResponse {
        val response = orderBookApi.getOrderBooks(book)
        println("response order book" + response)

        return response
    }

    suspend fun getAvaibleBook(): List<Book> {
        val response = avaibleBookApi.getOrderBooks().books.filter { it.book.contains("mxn") }
        return response
    }
}
