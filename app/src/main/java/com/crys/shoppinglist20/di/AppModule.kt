package com.crys.shoppinglist20.di

import android.content.Context
import androidx.room.Room
import com.crys.shoppinglist20.api.PhotoApi
import com.crys.shoppinglist20.data.db.ShoppingDatabase
import com.crys.shoppinglist20.data.repositories.ShoppingRepository
import com.crys.shoppinglist20.other.Constants.BASE_URL
import com.crys.shoppinglist20.other.Constants.SHOPPING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,
        ShoppingDatabase::class.java, SHOPPING_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideShoppingDao(db: ShoppingDatabase) = db.shoppingDao()

    @Singleton
    @Provides
    fun provideRepository(db: ShoppingDatabase, photoApi: PhotoApi) = ShoppingRepository(db, photoApi)

    @Singleton
    @Provides
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(PhotoApi::class.java)
}