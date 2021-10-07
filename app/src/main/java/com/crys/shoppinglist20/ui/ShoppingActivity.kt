package com.crys.shoppinglist20.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.databinding.ActivityShoppingBinding
import com.crys.shoppinglist20.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ShoppingItemAdapter(listOf(
            ShoppingItem("Banana", 3),
            ShoppingItem("Pickle", 5),
            ShoppingItem("Rabbit", 1),
            ShoppingItem("Hobbit", 4)
        ))

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

    }
}