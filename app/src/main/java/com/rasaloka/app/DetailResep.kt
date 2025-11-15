package com.rasaloka.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailResep : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resep)

        val btnBack: ImageView = findViewById(R.id.btnBack)
        val btnBookmark: ImageView = findViewById(R.id.btnBookmark)
        val imageResep: ImageView = findViewById(R.id.imageResep)
        val titleAppBar: TextView = findViewById(R.id.titleAppBar)
        val textDeskripsi: TextView = findViewById(R.id.textDeskripsi)
        val textLangkah: TextView = findViewById(R.id.textLangkah)
        val recyclerBahan: RecyclerView = findViewById(R.id.recyclerBahan)

        // Judul (ditampilkan di AppBar)
        titleAppBar.text = "Soto Ayam Jawa"

        // Deskripsi
        textDeskripsi.text = "Soto Ayam Jawa adalah masakan khas Indonesia dengan kuah kuning gurih dari kunyit dan rempah-rempah. Berisi ayam suwir, soun, tauge, dan telur rebus, disajikan hangat dengan pelengkap seperti jeruk nipis, daun bawang, dan sambal."

        // Langkah pembuatan
        textLangkah.text = """
            1. Haluskan bawang putih, bawang merah, kemiri, kunyit dan jahe.
            2. Tumis bumbu halus hingga harum lalu masukkan serai.
            3. Masukkan ayam dan air, masak hingga matang dan bumbu meresap.
            4. Koreksi rasa dengan garam, gula, dan penyedap.
            5. Sajikan dengan soun, tauge, telur rebus, dan jeruk nipis.
        """.trimIndent()

        // Gambar detail
        imageResep.setImageResource(R.drawable.soto)

        // Data bahan
        val listBahan = listOf(
            Bahan("Ayam (rebus, suwir)", "500 g", R.drawable.ayam_suwir),
            Bahan("Air", "2 Liter", R.drawable.air_rebus),
            Bahan("Bawang Putih", "3 Siung", R.drawable.baput),
            Bahan("Bawang Merah", "5 Siung", R.drawable.bamer),
            Bahan("Kemiri", "3 Butir", R.drawable.kemiri),
            Bahan("Kunyit", "2 cm", R.drawable.kunyit),
            Bahan("Jahe", "2 cm", R.drawable.jahe),
            Bahan("Serai", "2 Batang", R.drawable.serai),
            Bahan("Daun Jeruk", "3 Lembar", R.drawable.daun_jeruk),
            Bahan("Garam, Gula, Penyedap, Soun, Tauge, Telur", "Secukupnya", R.drawable.pelengkap)
        )

        // RecyclerView
        recyclerBahan.layoutManager = LinearLayoutManager(this)
        recyclerBahan.adapter = BahanAdapter(listBahan)

        // Tombol back
        btnBack.setOnClickListener { finish() }

        // Tombol bookmark
        btnBookmark.setOnClickListener {
            btnBookmark.isSelected = !btnBookmark.isSelected
        }
    }
}
