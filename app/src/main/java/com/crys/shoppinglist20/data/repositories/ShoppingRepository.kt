package com.crys.shoppinglist20.data.repositories

import com.crys.shoppinglist20.data.db.ShoppingDatabase
import com.crys.shoppinglist20.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.shoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.shoppingDao().delete(item)

    fun getAllShoppingItems() = db.shoppingDao().getAllShoppingItems()
}