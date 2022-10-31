package com.example.androidsmartmarket.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidsmartmarket.model.Datas
import com.example.androidsmartmarket.model.DeliveryTypes
import com.example.androidsmartmarket.model.Photo
import com.example.androidsmartmarket.model.Sellers
import java.io.Serializable

@Entity(tableName = "subscriber_data_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null,
    @ColumnInfo(name = "names")
    var names : Datas? = null,
    @ColumnInfo(name = "product_count")
    var product_count : Int? = null,
)
