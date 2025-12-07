package com.rasaloka.app.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R
import com.rasaloka.app.Home.Resep

class SemuaResepAdapter(
    private var listResep: List<Resep>,
    private val listener: OnResepClickListener
) : RecyclerView.Adapter<SemuaResepAdapter.ResepViewHolder>() {

    // Search bar: Menyimpan list awal untuk keperluan reset setelah search kosong
    private var originalList: List<Resep> = listResep.toList()

    inner class ResepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageResep: ImageView = itemView.findViewById(R.id.imageView)
        val namaResep: TextView = itemView.findViewById(R.id.text_nama_resep_grid)

        init {
            itemView.setOnClickListener {
                listener.onResepClick(listResep[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resep_grid, parent, false)
        return ResepViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        val resep = listResep[position]

        holder.imageResep.setImageResource(resep.gambarResId)
        holder.namaResep.text = resep.namaResep
    }

    override fun getItemCount(): Int = listResep.size

    fun updateData (newList: List<Resep>){
        listResep=newList
        notifyDataSetChanged()
    }

    // Search bar: fungsi untuk melakukan proses filter pencarian berdasarkan nama resep
    fun filter(query: String){
        val filtered = if(query.isEmpty()){
            originalList
        } else {
            // ignoreCase supaya pencarian tidak sensitif huruf besar/kecil
            originalList.filter {
                it.namaResep.contains(query, ignoreCase = true)
            }
        }
        // panggil updateData agar RecyclerView merefresh tampilan
        updateData(filtered)
    }

}

// Listener ketika item diklik
interface OnResepClickListener {
    fun onResepClick(resep: Resep)
}
