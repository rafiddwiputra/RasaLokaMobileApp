package com.rasaloka.app.DetailResep

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class DetailResepFragment : Fragment() {

    private val args: DetailResepFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_resep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgHeader: ImageView = view.findViewById(R.id.imageHeader)
        val textJudul: TextView = view.findViewById(R.id.textJudulHeader)
        val textDeskripsi: TextView = view.findViewById(R.id.textDeskripsi)
        val recyclerBahan: RecyclerView = view.findViewById(R.id.recyclerBahan)
        val textLangkah: TextView = view.findViewById(R.id.textLangkah)
        val btnBookmark: ImageView = view.findViewById(R.id.btnBookmark)

        textJudul.text = args.namaResep
        textDeskripsi.text = args.deskripsiResep
        textLangkah.text = args.langkahResep
        imgHeader.setImageResource(args.gambarResep.toInt())

        // Kode ini digunakan untuk menambahkan data bahan dari resep MASUKKAN DISINII!!!
        val bahanList = args.bahanResep.split("\n").map { item ->
            val cleanItem = item.trim()
            val data = cleanItem.split(" - ")

            val nama = data[0].trim()
            val jumlah = data[1].trim()

            // pilih gambar berdasarkan nama bahan
            val gambarBahan = when {
                nama.contains("Ayam", ignoreCase = true)->R.drawable.ayam_suwir
                nama.contains("Bawang Putih", ignoreCase = true)-> R.drawable.baput
                nama.contains("Bawang Merah", ignoreCase = true) ->R.drawable.bamer
                nama.contains("Kemiri", ignoreCase = true)->R.drawable.kemiri
                nama.contains("Jahe", ignoreCase = true)-> R.drawable.jahe
                nama.contains("Serai", ignoreCase = true)->R.drawable.serai
                nama.contains("Daun Jeruk", ignoreCase = true)->R.drawable.daun_jeruk
                nama.contains("Air", ignoreCase = true)->R.drawable.air_rebus
                nama.contains("Telur", ignoreCase = true)->R.drawable.pelengkap
                nama.contains("Garam, GUla, Penyedap, Soun, Tuge, Telur", ignoreCase = true)->R.drawable.pelengkap

                else -> R.drawable.ayam_suwir // Semisal kalau bahan tidak ada maka akan otomatis menampilkan bahan dari ayam_suwir
            }

            Bahan(nama, jumlah, gambarBahan)

//        Kode ini bisa dihapus
        }.distinctBy { it.nama }
        Log.d("CEK_BAHAN", "<<<<<<<<<<<<<<<<<<<<<<<<")
        Log.d("CEK_BAHAN", args.bahanResep)
        Log.d("CEK_BAHAN", ">>>>>>>>>>>>>>>>>>>>>>>>")

        val bahanRaw = args.bahanResep.split("\n")
        bahanRaw.forEachIndexed { index, item ->
            Log.d("ITEM_KE${index}", "[$item]")
        }
//        =======================================================================

        recyclerBahan.layoutManager = LinearLayoutManager(requireContext())
        recyclerBahan.adapter = BahanAdapter(bahanList)

        btnBookmark.setOnClickListener {
            btnBookmark.isSelected = !btnBookmark.isSelected
        }

    }
}
