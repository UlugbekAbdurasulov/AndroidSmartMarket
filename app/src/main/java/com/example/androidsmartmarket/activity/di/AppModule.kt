package com.example.androidsmartmarket.activity.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidsmartmarket.database.MedicineDatabase
import com.example.androidsmartmarket.database.MedicineRepository
import com.example.androidsmartmarket.database.dao.ProductDao
import com.example.androidsmartmarket.network.RetrofitHttp.IS_TESTER
import com.example.androidsmartmarket.network.RetrofitHttp.SERVER_DEVELOPMENT
import com.example.androidsmartmarket.network.RetrofitHttp.SERVER_PRODUCTION
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideStudentDao(@ApplicationContext appContext: Context) : ProductDao {
        return MedicineDatabase.getInstance(appContext).subscriberDao
    }

    @Provides
    fun provideStudentDBRepository(studentDao: ProductDao) = MedicineRepository(studentDao)

    @Provides
    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService(): PhotosService {
        return retrofitClient().create(PhotosService::class.java)
    }

}