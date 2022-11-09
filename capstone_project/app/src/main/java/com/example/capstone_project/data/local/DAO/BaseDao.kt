package com.example.capstone_project.data.local.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: T)

    @Insert()
    fun insert(models: Array<T>)

    @Update()
    fun update(model: T)

    @Delete()
    fun delete(model: T)
}
