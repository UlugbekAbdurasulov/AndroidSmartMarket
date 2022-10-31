package com.example.androidsmartmarket.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidsmartmarket.database.entity.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Product): Long

    @Update
    suspend fun updateSubscriber(subscriber: Product) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Product) : Int

    @Query("DELETE FROM subscriber_data_table" )
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Product>>

}