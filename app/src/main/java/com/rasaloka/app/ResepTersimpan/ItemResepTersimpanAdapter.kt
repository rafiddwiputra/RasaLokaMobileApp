package com.rasaloka.app.ResepTersimpan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class ItemResepTersimpanAdapter(
    private val items: MutableList<ResepTersimpan>,
    private val onDelete: (Int) -> Unit,
    private val onClick: (ResepTersimpan) -> Unit  // tambahan untuk ketika resep tersimpan diklik masuk ke detail resep
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

        // === tombol delete ===
        holder.btnDelete.setOnClickListener {
            val prefs = holder.itemView.context.getSharedPreferences("BOOKMARKS", 0)

            // Hapus semua data resep berdasarkan format penyimpanan baru
            prefs.edit()
                .remove("${item.title}_saved")
                .remove("${item.title}_desc")
                .remove("${item.title}_img")
                .remove("${item.title}_bahan")
                .remove("${item.title}_langkah")
                .apply()

            onDelete(position) // hapus dari list recyclerview & refresh
        }
        // tambahan agar resep tersimpan jika diklik masuk ke detailresep
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }


    override fun getItemCount(): Int = items.size
}
