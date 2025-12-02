package com.rasaloka.app.DetailResep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class BahanAdapter(private val listBahan: List<Bahan>) :
    RecyclerView.Adapter<BahanAdapter.BahanViewHolder>() {

    inner class BahanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBahan: ImageView = itemView.findViewById(R.id.imgBahan)
        val tvNamaBahan: TextView = itemView.findViewById(R.id.tvNamaBahan)
        val tvJumlahBahan: TextView = itemView.findViewById(R.id.tvJumlahBahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BahanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bahan, parent, false)
        return BahanViewHolder(view)
    }

    override fun onBindViewHolder(holder: BahanViewHolder, position: Int) {
        val bahan = listBahan[position]

        holder.imgBahan.setImageResource(bahan.gambar)
        holder.tvNamaBahan.text = bahan.nama
        holder.tvJumlahBahan.text = bahan.jumlah
    }

    override fun getItemCount(): Int = listBahan.size
}
