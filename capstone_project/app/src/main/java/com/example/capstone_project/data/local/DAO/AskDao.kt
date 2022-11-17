package com.example.capstoneproject.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstoneproject.data.local.entities.AskEntity
@Dao
interface AskDao : BaseDao<AskEntity> {
    @Query("SELECT * FROM ask")
    suspend fun getAllAsks(): List<AskEntity>
}
