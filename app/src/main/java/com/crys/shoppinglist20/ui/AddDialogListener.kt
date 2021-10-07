package com.crys.shoppinglist20.ui

import com.crys.shoppinglist20.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}