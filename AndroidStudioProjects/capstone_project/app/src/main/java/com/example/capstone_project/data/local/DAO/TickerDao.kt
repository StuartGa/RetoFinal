package com.example.capstone_project.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstone_project.data.local.entities.TickerEntity
import com.example.capstone_project.data.network.entities.model.Ticker
@Dao
interface TickerDao : BaseDao<TickerEntity> {
    @Query("SELECT * FROM ticker")
    fun selectTicker(): Ticker
}
