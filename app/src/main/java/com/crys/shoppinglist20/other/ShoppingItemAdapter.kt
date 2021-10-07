package com.crys.shoppinglist20.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.databinding.ShoppingItemBinding
import com.crys.shoppinglist20.ui.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
    inner class ShoppingViewHolder(val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.binding.tvName.text = curShoppingItem.name
        holder.binding.tvAmount.text = curShoppingItem.amount.toString()


        holder.binding.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.binding.ivAdd.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.binding.ivRemove.setOnClickListener {
            if(curShoppingItem.amount > 1) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}