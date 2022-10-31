package com.example.androidsmartmarket.database


import android.content.Context
import androidx.room.*
import com.example.androidsmartmarket.database.dao.ProductDao
import com.example.androidsmartmarket.database.entity.Product
import com.example.androidsmartmarket.utils.HobbiesTypeConverter

/**
 * Two main tables are created in the database,
 * the first one is for temporary data storage and the second one is for permanent storage
 * **/


@Database(entities = [Product::class], version = 1)
@TypeConverters(HobbiesTypeConverter::class)
abstract class MedicineDatabase : RoomDatabase() {
    abstract val subscriberDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: MedicineDatabase? = null

        fun getInstance(context: Context): MedicineDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicineDatabase::class.java,
                        "medicine_dao"
                    ).build()
                }
                return instance
            }
        }

    }
}