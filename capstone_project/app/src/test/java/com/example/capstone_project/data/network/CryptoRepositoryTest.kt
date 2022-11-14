package com.example.capstone_project.data.network

import com.example.capstone_project.data.Respository.CriptoRepository
import com.example.capstone_project.data.network.service.CryptoService
import com.example.capstone_project.domain.model.BookDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CryptoRepositoryTest {
    @RelaxedMockK
    private lateinit var cryptoRepository: CriptoRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test repository`() = runBlocking {
        cryptoRepository.getAvaibleBookApi()
        coVerify { cryptoRepository.getAvaibleBookLocal() }
    }

    @Test
    fun `test api`() = runBlocking {
        coEvery { cryptoRepository.getAvaibleBookApi() } returns emptyList<BookDomain>()
        var myList = emptyList<BookDomain>()

        myList = cryptoRepository.getAvaibleBookApi()
        println("size: ${myList.size}")
        assert(myList.size == 0)
    }
}