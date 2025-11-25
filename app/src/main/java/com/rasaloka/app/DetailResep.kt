package com.rasaloka.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailResep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_resep)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBookmark: ImageView = findViewById(R.id.btnBookmark)
        val textDeskripsi: TextView = findViewById(R.id.textDeskripsi)
        val textLangkah: TextView = findViewById(R.id.textLangkah)
        val recyclerBahan: RecyclerView = findViewById(R.id.recyclerBahan)

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

        // RecyclerViewHeader
        recyclerBahan.layoutManager = LinearLayoutManager(this)
        recyclerBahan.adapter = BahanAdapter(listBahan)
        // DATA HEADER
        val headerList = listOf(
            HeaderDetailResep
                ("Soto Ayam Jawa",
                R.drawable.soto
            )
        )

// SETUP RECYCLER HEADER
        val headerRecycler: RecyclerView = findViewById(R.id.headerDetailResep)
        headerRecycler.layoutManager = LinearLayoutManager(this)
        headerRecycler.adapter = HeaderDetailResepAdapter(headerList)


        // Tombol bookmark
        btnBookmark.setOnClickListener {
            btnBookmark.isSelected = !btnBookmark.isSelected
        }
    }
}