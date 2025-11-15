package com.rasaloka.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResepAdapter(private val listResep: List<Resep>) :
    RecyclerView.Adapter<ResepAdapter.ResepViewHolder>() {

    class ResepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageResep: ImageView = itemView.findViewById(R.id.imageResep)
        val tvNamaResep: TextView = itemView.findViewById(R.id.tvNamaResep)
        val tvDeskripsiResep: TextView = itemView.findViewById(R.id.tvDeskripsiResep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resep, parent, false)
        return ResepViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        val resep = listResep[position]
        holder.imageResep.setImageResource(resep.imageResId)
        holder.tvNamaResep.text = resep.namaMakanan
        holder.tvDeskripsiResep.text = resep.deskripsiMakanan
    }

    override fun getItemCount(): Int = listResep.size
}
