package com.example.capstone_project.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstone_project.data.local.entities.BidsEntity
@Dao
interface BidDao : BaseDao<BidsEntity> {
    @Query("SELECT * FROM bid")
    suspend fun getAllBids(): List<BidsEntity>
}
