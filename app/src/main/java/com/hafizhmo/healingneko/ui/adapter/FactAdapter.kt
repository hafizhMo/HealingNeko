package com.hafizhmo.healingneko.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafizhmo.healingneko.data.local.entity.FactEntity
import com.hafizhmo.healingneko.databinding.ItemListFactBinding

class FactAdapter(
    private val facts: List<FactEntity>,
    private val listener: (FactEntity) -> Unit
) : RecyclerView.Adapter<FactAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListFactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(fact: FactEntity, i: Int, listener: (FactEntity) -> Unit) {
            with(binding) {
                textDetail.text = fact.fact
                textIndex.text = "0$i"
                if (i > 9)
                    textIndex.text = i.toString()

            }
            itemView.setOnClickListener { listener(fact) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactAdapter.ViewHolder {
        return ViewHolder(
            ItemListFactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FactAdapter.ViewHolder, position: Int) {
        holder.onBind(facts[position], position + 1, listener)
    }

    override fun getItemCount(): Int = facts.size
}