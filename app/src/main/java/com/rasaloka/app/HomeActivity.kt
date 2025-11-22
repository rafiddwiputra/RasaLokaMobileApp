package com.rasaloka.app

import android.os.Bundle
import android.text.Layout
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class HomeActivity : AppCompatActivity() {
    private lateinit var indicatorsLayout: LinearLayout
    private lateinit var semuaResepAdapter: SemuaResepAdapter
    private lateinit var resepList: List<Resep>
    private lateinit var semuaResep: List<Resep>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menghubungkan Data Resep Dengan Menggunakan ID
        val viewPager: ViewPager2 = findViewById(R.id.view_pager_resep)
        indicatorsLayout = findViewById(R.id.indikator_slider)

        // Siapkan Data Resep Untuk Slider
        resepList = listOf(
            Resep("Steak Beef",
                "Tekstur lembut berpadu dengan karamelisasi sempurna",
                R.drawable.steakbeef,
                "Rekomendasi"),
            Resep("Capcay",
                "Krim kental dan kejutan keju Parmesan Italia",
                R.drawable.capcay,
                "Rekomendasi"),
            Resep("Ayam",
                "Rasa gurih yang menambah selera",
                R.drawable.ayam_bakar,
                "Rekomendasi")
        )

        // Hubungkan Adapter ke ViewPager2 atau Slider
        val adapter = SliderAdapter(resepList)
        viewPager.adapter = adapter

        // Inisialisasi dan menghubungkan Indikator Titik
        setupIndicators(resepList.size)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })


        // Inisialisasi KategoriResep
        // Menghubungkan Rv KategoriResep
        val kategoriData = listOf(
            KategoriResep("Ayam", R.drawable.ayam),
            KategoriResep("Daging", R.drawable.ikondaging),
            KategoriResep("Sayur", R.drawable.ikonsayur),
            KategoriResep("Telur", R.drawable.ikontelur)
        )

        val rvKategoriResep = findViewById<RecyclerView>(R.id.rv_kategori_resep)
        rvKategoriResep.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val kategoriAdapter = KategoriResepAdapter(kategoriData, object : OnKategoriClickListener {
            override fun onKategoriClick(kategori: KategoriResep) {
                Toast.makeText(
                    this@HomeActivity,
                    "Kategori Dipilih: ${kategori.namaKategori}",
                    Toast.LENGTH_SHORT
                ).show()
                // Memanggil Fungsi Filter
                filterResepByKategori(kategori.namaKategori)
            }
        })
        rvKategoriResep.adapter=kategoriAdapter

        // Semua Resep Grid
        // semua data resep akan dikumpulkan dalam satu variabel agar bisa difilter
        semuaResep=listOf(
            Resep("Soto Jawa", "", R.drawable.soto, "Sayur"),
            Resep("Capcay", "", R.drawable.capcay, "Sayur"),
            Resep("Ayam Bakar", "", R.drawable.ayam_bakar, "Ayam"),
            Resep("Steak Beef", "", R.drawable.steakbeef, "Daging")
        )

        val rvSemuaResep = findViewById<RecyclerView>(R.id.rv_semua_resep)
        rvSemuaResep.layoutManager = GridLayoutManager(this, 2)

        semuaResepAdapter = SemuaResepAdapter(semuaResep, object : OnResepClickListener {
            override fun onResepClick(resep: Resep) {
                Toast.makeText(
                    this@HomeActivity,
                    "Resep Dipilih: ${resep.namaResep}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        rvSemuaResep.adapter = semuaResepAdapter
    } // Titik Akhir onCreate

    // Fungsi Filter Kategori Resep
    // Fungsi untuk memfilter data resep berdasarkan kategori, fungsi ini dipanggil ketika user mengklik kategori di RV
    private fun filterResepByKategori(kategori: String) {
        // Mancari hanya resep yang kategorinya cocok
        val filteredList = semuaResep.filter {
            it.kategori.equals(kategori, ignoreCase = true)
        }
        // Memperbarui adapter dengan data yang sudah difilter
        semuaResepAdapter.updateData(filteredList)
    }

    // Fungsi Bantuan untuk Indikator
    private fun setupIndicators(count: Int){
        // Hapus Indikator lama (jika ada)
        indicatorsLayout.removeAllViews()

        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // Membarikan jarak antar titik
        layoutParams.setMargins(8, 0,8,0)

        for (i in 0 until count){
            val indicators= ImageView(this)
            indicators.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.indikator_aktif)
            )
            indicators.layoutParams=layoutParams
            indicatorsLayout.addView(indicators) // Menambahkan titik ke Linear Layout
        }
        updateIndicators(0) // Set titik pertama aktif
    }
    private fun updateIndicators(selectedPosition: Int) {
        val childCount = indicatorsLayout.childCount

        // Perhatikan sintaks for dan kurung kurawal
        for (i in 0 until childCount) {
            val imageView = indicatorsLayout.getChildAt(i) as ImageView

            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    // Menggunakan kondisi if/else di dalam Drawable
                    if (i == selectedPosition) R.drawable.indikator_aktif else R.drawable.indikator_tidakaktif)
            )

        }

    }
}
