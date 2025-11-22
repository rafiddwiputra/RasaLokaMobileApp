package com.rasaloka.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 1. Definisikan Interface Listener
interface OnKategoriClickListener {
    // Fungsi ini akan dipanggil, membawa objek Kategori yang diklik
    fun onKategoriClick(kategori: KategoriResep)
}

// 2. Modifikasi Kelas untuk Menerima Listener
class KategoriResepAdapter(
    private val kategoriList: List<KategoriResep>,
    private val listener: OnKategoriClickListener // <-- Parameter listener baru
) : RecyclerView.Adapter<KategoriResepAdapter.KategoriViewHolder>() {

    // ... (Kode KategoriViewHolder dan onCreateViewHolder Anda tetap sama)

    // Bagian ViewHolder (Mewakili item_kategori.xml)
    class KategoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconKategori: ImageView = itemView.findViewById(R.id.icon_kategori)
        val namaKategori: TextView = itemView.findViewById(R.id.nama_kategori_resep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)
        return KategoriViewHolder(view)
    }

    // 3. Tambahkan Logika Klik di onBindViewHolder
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = kategoriList[position]

        holder.namaKategori.text = kategori.namaKategori
        holder.iconKategori.setImageResource(kategori.ikonKategori)

        // Menetapkan listener ke seluruh item View
        holder.itemView.setOnClickListener {
            // Panggil fungsi click listener, meneruskan objek kategori yang diklik
            listener.onKategoriClick(kategori)
        }
    }

    override fun getItemCount(): Int = kategoriList.size
}