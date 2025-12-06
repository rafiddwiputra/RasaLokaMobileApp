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

    private fun getPrefs() = requireContext().getSharedPreferences("BOOKMARKS", 0)

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
                nama.contains("Ayam Suwir", ignoreCase = true) -> R.drawable.ayam_suwir
                nama.contains("Bawang Putih", ignoreCase = true) -> R.drawable.baput
                nama.contains("Bawang Merah", ignoreCase = true) -> R.drawable.bamer
                nama.contains("Kemiri", ignoreCase = true) -> R.drawable.kemiri
                nama.contains("Jahe", ignoreCase = true) -> R.drawable.jahe
                nama.contains("Serai", ignoreCase = true) -> R.drawable.serai
                nama.contains("Daun Jeruk", ignoreCase = true) -> R.drawable.daun_jeruk
                nama.contains("Air", ignoreCase = true) -> R.drawable.air_rebus
                nama.contains("Telur", ignoreCase = true) -> R.drawable.telur
                nama.contains("Garam, Gula, Penyedap, Soun, dan Tauge", ignoreCase = true) -> R.drawable.pelengkap
                nama.contains("Wortel", ignoreCase = true) -> R.drawable.wortel
                nama.contains("Bakso", ignoreCase = true) -> R.drawable.bakso
                nama.contains("Kembang Kol", ignoreCase = true) -> R.drawable.kembang_kol
                nama.contains("Kol", ignoreCase = true) -> R.drawable.kol
                nama.contains("Sawi Hijau", ignoreCase = true) -> R.drawable.sawi_hijau
                nama.contains("Garam", ignoreCase = true) -> R.drawable.garam
                nama.contains("Merica", ignoreCase = true) -> R.drawable.merica
                nama.contains("Minyak", ignoreCase = true) -> R.drawable.minyak
                nama.contains("Kecap Asin", ignoreCase = true) -> R.drawable.kecap_asin
                nama.contains("Saus Tiram", ignoreCase = true) -> R.drawable.saus_tiram
                nama.contains("Ayam", ignoreCase = true) -> R.drawable.ayam_potong
                nama.contains("Ketumbar", ignoreCase = true) -> R.drawable.ketumbar
                nama.contains("Daun Salam", ignoreCase = true) -> R.drawable.daun_salam
                nama.contains("Kecap Manis", ignoreCase = true) -> R.drawable.kecap_manis
                nama.contains("Gula Merah", ignoreCase = true) -> R.drawable.gula_merah
                nama.contains("Lengkuas", ignoreCase = true) -> R.drawable.lengkuas
                nama.contains("Kaldu Bubuk", ignoreCase = true) -> R.drawable.kaldu_bubuk
                nama.contains("Daging Sapi", ignoreCase = true) -> R.drawable.daging
                nama.contains("Mentega", ignoreCase = true) -> R.drawable.mentega
                else -> R.drawable.ayam_suwir
            }

            Bahan(nama, jumlah, gambarBahan) // Kode ini bisa dihapus
        }.distinctBy { it.nama }

        Log.d("CEK_BAHAN", "<<<<<<<<<<<<<<<<<<<<<<<<")
        Log.d("CEK_BAHAN", args.bahanResep)
        Log.d("CEK_BAHAN", ">>>>>>>>>>>>>>>>>>>>>>>>")

        val bahanRaw = args.bahanResep.split("\n")
        bahanRaw.forEachIndexed { index, item ->
            Log.d("ITEM_KE${index}", "[$item]")
        }

        // =======================================================================
        recyclerBahan.layoutManager = LinearLayoutManager(requireContext())
        recyclerBahan.adapter = BahanAdapter(bahanList)

        // Cek apakah resep sudah tersimpan sebelumnya
        val prefs = getPrefs()
        var saved = prefs.getBoolean("${args.namaResep}_saved", false)

// Set gambar icon sesuai status saat halaman dibuka
        btnBookmark.setImageResource(if (saved) R.drawable.ic_save_filled else R.drawable.ic_save_orange)

        btnBookmark.setOnClickListener {
            saved = !saved

            val key = args.namaResep // supaya tidak nulis berulang

            if (saved) {
                // SIMPAN DATA LENGKAP
                prefs.edit()
                    .putBoolean("${key}_saved", true)
                    .putString("${key}_desc", args.deskripsiResep)
                    .putInt("${key}_img", args.gambarResep.toInt())
                    .putString("${key}_bahan", args.bahanResep)
                    .putString("${key}_langkah", args.langkahResep)
                    .apply()
            } else {
                // HAPUS DATA LENGKAP
                prefs.edit()
                    .remove("${key}_saved")
                    .remove("${key}_desc")
                    .remove("${key}_img")
                    .remove("${key}_bahan")
                    .remove("${key}_langkah")
                    .apply()
            }

            btnBookmark.setImageResource(
                if (saved) R.drawable.ic_save_filled else R.drawable.ic_save_orange
            )
        }

    }
}
