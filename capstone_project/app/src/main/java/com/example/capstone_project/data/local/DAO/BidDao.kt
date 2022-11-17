package com.example.capstoneproject.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstoneproject.data.local.entities.BidsEntity
@Dao
interface BidDao : BaseDao<BidsEntity> {
    @Query("SELECT * FROM bid")
    suspend fun getAllBids(): List<BidsEntity>
}
