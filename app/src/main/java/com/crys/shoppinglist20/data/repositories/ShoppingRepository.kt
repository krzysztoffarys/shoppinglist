package com.crys.shoppinglist20.data.repositories

import com.crys.shoppinglist20.api.PhotoApi
import com.crys.shoppinglist20.data.db.ShoppingDatabase
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import retrofit2.Retrofit

class ShoppingRepository(
    private val db: ShoppingDatabase,
    private val photoApi: PhotoApi
) {
    suspend fun upsert(item: ShoppingItem) = db.shoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.shoppingDao().delete(item)

    fun getAllShoppingItems() = db.shoppingDao().getAllShoppingItems()

    suspend fun providePhoto(tag: String) = photoApi.getPhotos(tags = tag)
}