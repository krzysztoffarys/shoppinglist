package com.crys.shoppinglist20.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.databinding.ShoppingItemBinding

class ShoppingItemAdapter(
    private var items: List<ShoppingItem>
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
    inner class ShoppingViewHolder(val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.binding.tvName.text = items[position].name
        holder.binding.tvAmount.text = items[position].amount.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}