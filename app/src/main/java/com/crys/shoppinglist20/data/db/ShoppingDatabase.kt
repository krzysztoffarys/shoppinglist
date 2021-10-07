package com.crys.shoppinglist20.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crys.shoppinglist20.data.db.entities.ShoppingItem


@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}