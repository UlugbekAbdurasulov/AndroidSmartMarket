package com.example.androidsmartmarket.database

import com.example.androidsmartmarket.database.dao.ProductDao
import com.example.androidsmartmarket.database.entity.Product
import javax.inject.Inject

class MedicineRepository @Inject constructor(private val dao : ProductDao) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Product) : Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Product) : Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Product) : Int {
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}