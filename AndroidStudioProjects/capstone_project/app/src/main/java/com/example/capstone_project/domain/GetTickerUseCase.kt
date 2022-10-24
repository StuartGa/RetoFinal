package com.example.capstone_project.domain

import com.example.capstone_project.data.TickerRepository
import com.example.capstone_project.data.entities.model.Ticker

class GetTickerUseCase {
    private val repository = TickerRepository()

    suspend operator fun invoke(): Ticker? = repository.getTicker().ticker

}