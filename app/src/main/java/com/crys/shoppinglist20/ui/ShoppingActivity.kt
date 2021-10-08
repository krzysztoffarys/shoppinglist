package com.crys.shoppinglist20.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.databinding.ActivityShoppingBinding
import com.crys.shoppinglist20.other.ShoppingItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding
    private val viewModel: ShoppingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = ShoppingItemAdapter(listOf(), viewModel, this)

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        item.url = viewModel.provideUrl(item.name)
                        viewModel.upsert(item)
                    }
                }
            ).show()
        }
    }
}