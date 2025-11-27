package com.rasaloka.app.DetailResep

import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class DetailResep : androidx.appcompat.app.AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(_root_ide_package_.com.rasaloka.app.R.layout.activity_detail_resep)
        _root_ide_package_.androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(findViewById(
            _root_ide_package_.com.rasaloka.app.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(_root_ide_package_.androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBookmark: android.widget.ImageView = findViewById(_root_ide_package_.com.rasaloka.app.R.id.btnBookmark)
        val textDeskripsi: android.widget.TextView = findViewById(_root_ide_package_.com.rasaloka.app.R.id.textDeskripsi)
        val textLangkah: android.widget.TextView = findViewById(_root_ide_package_.com.rasaloka.app.R.id.textLangkah)
        val recyclerBahan: androidx.recyclerview.widget.RecyclerView = findViewById(
            _root_ide_package_.com.rasaloka.app.R.id.recyclerBahan)

        // Deskripsi
        textDeskripsi.text =
            "Soto Ayam Jawa adalah masakan khas Indonesia dengan kuah kuning gurih dari kunyit dan rempah-rempah. Berisi ayam suwir, soun, tauge, dan telur rebus, disajikan hangat dengan pelengkap seperti jeruk nipis, daun bawang, dan sambal."

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
            Bahan(
                "Ayam (rebus, suwir)",
                "500 g",
                com.rasaloka.app.R.drawable.ayam_suwir
            ),
            Bahan(
                "Air",
                "2 Liter",
                com.rasaloka.app.R.drawable.air_rebus
            ),
            Bahan(
                "Bawang Putih",
                "3 Siung",
                com.rasaloka.app.R.drawable.baput
            ),
            Bahan(
                "Bawang Merah",
                "5 Siung",
                com.rasaloka.app.R.drawable.bamer
            ),
            Bahan(
                "Kemiri",
                "3 Butir",
                com.rasaloka.app.R.drawable.kemiri
            ),
            Bahan(
                "Kunyit",
                "2 cm",
                com.rasaloka.app.R.drawable.kunyit
            ),
            Bahan(
                "Jahe",
                "2 cm",
                com.rasaloka.app.R.drawable.jahe
            ),
            Bahan(
                "Serai",
                "2 Batang",
                com.rasaloka.app.R.drawable.serai
            ),
            Bahan(
                "Daun Jeruk",
                "3 Lembar",
                com.rasaloka.app.R.drawable.daun_jeruk
            ),
            Bahan(
                "Garam, Gula, Penyedap, Soun, Tauge, Telur",
                "Secukupnya",
                com.rasaloka.app.R.drawable.pelengkap
            )
        )

        // RecyclerViewHeader
        recyclerBahan.layoutManager =
            _root_ide_package_.androidx.recyclerview.widget.LinearLayoutManager(this)
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