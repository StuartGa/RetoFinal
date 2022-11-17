package com.example.capstoneproject.data.local.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.capstoneproject.data.local.entities.BookEntity
@Dao
interface BookDao : BaseDao<BookEntity> {
    @Query("SELECT * FROM book")
    suspend fun getAllBook(): List<BookEntity>
}
