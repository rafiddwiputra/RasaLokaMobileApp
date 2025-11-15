package com.rasaloka.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemResepTersimpanAdapter(
    private val items: MutableList<ResepTersimpan>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<ItemResepTersimpanAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgRecipe: ImageView = view.findViewById(R.id.imgRecipe)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtDesc: TextView = view.findViewById(R.id.txtDesc)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resep_tersimpan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.imgRecipe.setImageResource(item.image)
        holder.txtTitle.text = item.title
        holder.txtDesc.text = item.description

        holder.btnDelete.setOnClickListener {
            onDelete(position)
        }
    }

    override fun getItemCount(): Int = items.size
}