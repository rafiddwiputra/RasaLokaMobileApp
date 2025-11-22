package com.rasaloka.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SliderAdapter(private val resepList: List<Resep>):
    RecyclerView.Adapter<SliderAdapter.ResepViewHolder>() {

    // Bagian ViewHolder(mewakili file item_resep_rekomendasi.xml
    class ResepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaResep: TextView = itemView.findViewById(R.id.text_judul_resep_rekomendasi)
        val deskripsiResep: TextView = itemView.findViewById(R.id.text_deskripsi_resep)
        val gambarResep: ImageView = itemView.findViewById(R.id.image_resep_rekomendasi)
    }

    // onCreateViewHolder: bertugas untuk membuat dan menginflate layout dari item_resep_slider.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
//    Merubah XML layout menjadi View
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resep_slider, parent, false)
        return ResepViewHolder(view)
    }

    //    onBindViewHolder: Bertugas untuk mengisi data ke ViewHolder
    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        val resep = resepList[position]
//    Mengisi data ke komponen view
        holder.namaResep.text = resep.namaResep
        holder.deskripsiResep.text = resep.deskripsiResep
        holder.gambarResep.setImageResource(resep.gambarResId)
    }

    //getItemCount:memberi tahu berapa banyak item yang ada di dalam daftar resep
    override fun getItemCount(): Int = resepList.size

}


