package com.crys.shoppinglist20.di

import android.content.Context
import androidx.room.Room
import com.crys.shoppinglist20.data.db.ShoppingDatabase
import com.crys.shoppinglist20.other.Constants.SHOPPING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,
        ShoppingDatabase::class.java, SHOPPING_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(db: ShoppingDatabase) = db.shoppingDao()

}