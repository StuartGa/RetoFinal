package com.example.capstoneproject.domain

import com.example.capstoneproject.data.repository.CriptoRepository
import com.example.capstoneproject.domain.model.BookDomain
import com.example.capstoneproject.domain.usescase.GetAvaibleBookUseCase
import com.example.capstoneproject.presentation.util.Util
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkObject
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAvaibleBookUseCaseTest {
    @RelaxedMockK
    private lateinit var cryptoRepository: CriptoRepository

    lateinit var getAvailableBookUseCase: GetAvaibleBookUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mockkObject(Util)
        getAvailableBookUseCase = GetAvaibleBookUseCase(cryptoRepository)
    }

    @Test
    fun `Si hay conexion a internet y getAllAvailableBooksFromApi regresa una lista con datos`() = runBlocking {
        // given
        val myList = listOf(BookDomain("btc_mxn", 4000.0, 200.00000, 0.00000030000))
        every { Util.isNetworkEnabled() } returns true
        coEvery { cryptoRepository.getAvaibleBookApi() } returns myList

        // when
        val response = getAvailableBookUseCase

        // then
        coVerify(exactly = 1) { cryptoRepository.insertAvailableBooks(any()) }
        assert(myList == response.invoke())
    }

    @Test
    fun `Si hay conexion a internet y getAllAvailableBooksFromApi regresa una emptylist`() = runBlocking {
        // given
        var myList = emptyList<BookDomain>()
        every { Util.isNetworkEnabled() } returns true
        coEvery { cryptoRepository.getAvaibleBookApi() } returns myList

        // when
        val response = getAvailableBookUseCase

        // then
        coVerify(exactly = 1) { cryptoRepository.getAvaibleBookLocal() }
        assert(myList == response.invoke())
    }

    @Test
    fun `No hay conexion a internet y getAllAvailableBooksFromApi regresa una lista`() = runBlocking {
        // given
        val myList = listOf(BookDomain("btc_mxn", 4000.0, 200.00000, 0.00000030000))
        every { Util.isNetworkEnabled() } returns false
        coEvery { cryptoRepository.getAvaibleBookLocal() } returns myList

        // when
        val response = getAvailableBookUseCase

        // then
        coVerify(exactly = 1) { cryptoRepository.getAvaibleBookLocal() }
        assert(myList == response.invoke())
    }
}
