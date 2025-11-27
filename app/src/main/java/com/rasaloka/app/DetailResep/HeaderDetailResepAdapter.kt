package com.rasaloka.app.DetailResep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class HeaderDetailResepAdapter(
    private val listHeader: List<HeaderDetailResep>
) : RecyclerView.Adapter<HeaderDetailResepAdapter.HeaderViewHolder>() {

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageHeader: ImageView = itemView.findViewById(R.id.imageHeader)
        val textJudulHeader: TextView = itemView.findViewById(R.id.textJudulHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val data = listHeader[position]
        holder.imageHeader.setImageResource(data.imageRes)
        holder.textJudulHeader.text = data.judul
    }

    override fun getItemCount(): Int = listHeader.size
}