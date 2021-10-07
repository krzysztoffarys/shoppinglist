package com.crys.shoppinglist20.ui

import androidx.lifecycle.ViewModel
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.data.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) = GlobalScope.launch {
            repository.upsert(item)
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}