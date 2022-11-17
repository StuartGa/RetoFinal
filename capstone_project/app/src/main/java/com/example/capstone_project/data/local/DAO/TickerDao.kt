package com.example.capstoneproject.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstoneproject.data.local.entities.TickerEntity
import com.example.capstoneproject.data.network.entities.model.Ticker
@Dao
interface TickerDao : BaseDao<TickerEntity> {
    @Query("SELECT * FROM ticker")
    fun selectTicker(): Ticker
}
