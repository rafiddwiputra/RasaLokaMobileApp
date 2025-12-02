package com.rasaloka.app.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rasaloka.app.R

class HomeFragment : Fragment() {
    private lateinit var indicatorsLayout: LinearLayout
    private lateinit var resepList: List<Resep>
    private lateinit var semuaResep: List<Resep>
    private lateinit var semuaResepAdapter: SemuaResepAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // Semua Logika Inisialisasi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menghubungkan Data Resep Dengan ID Resep
        val viewPager: ViewPager2 =view.findViewById(R.id.view_pager_resep)
        indicatorsLayout = view.findViewById(R.id.indikator_slider)

        // Data Resep Bagian Slider
        resepList = listOf(
            Resep(
                "Steak Beef",
                "Tekstur lembut berpadu dengan karamelisasi sempurna",
                R.drawable.steakbeef,
                "Rekomendasi",
                "Daging sapi, mentega, lada, garam",
                "1. Panaskan mentega\n2. Panggang daging\n3. Sajikan hangat"
            ),
            Resep(
                "Capcay",
                "krim kental dan kejutan keju Permesan Italia",
                R.drawable.capcay,
                "Rekomendasi",
                "Sayuran campur, bawang putih, saus tiram",
                "1. Tumis bawang\n2. Masukkan sayuran\n3. Beri saus tiram"
            ),
            Resep(
                "Ayam",
                "Rasa gurih yang menambah selera",
                R.drawable.ayam_bakar,
                "Rekomendasi",
                "Ayam, kecap, bawang putih",
                "1. Marinasi ayam\n2. Bakar dengan api kecil\n3. Sajikan"
            )
        )

        // Menghubungkan Adapterke ViewPager2 atau slider rekomendasi resep untuk anda
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

        // Inisialisasi Kategori Resep
        // Menghubungkan RV KategoriResep
        val KategoriData =listOf(
            KategoriResep("Ayam", R.drawable.ayam),
            KategoriResep("Daging", R.drawable.ikondaging),
            KategoriResep("Sayur", R.drawable.ikonsayur),
            KategoriResep("Telur", R.drawable.ikontelur)
        )

        val rvKategoriResep=view.findViewById<RecyclerView>(R.id.rv_kategori_resep)
        // Perbaikan: Ganti kode This dengan menggunakan requireContext()
        rvKategoriResep.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val KategoriAdapter = KategoriResepAdapter(KategoriData, object : OnKategoriClickListener{
            override fun onKategoriClick(Kategori: KategoriResep) {
                Toast.makeText(
                    requireContext(),
                    "Kategori Dipilih: ${Kategori.namaKategori}",
                    Toast.LENGTH_SHORT
                ).show()
                // Memanggil Fungsi Filter
                filterResepByKategori(Kategori.namaKategori)
            }
        })
        rvKategoriResep.adapter=KategoriAdapter

        // Semua Resep Grid
        // Menambahkan Semua Data Resep akan dikumpulkan dalam satu variabel agar bisa difilter
        semuaResep = listOf(
            Resep(
                "Soto Jawa",
                "Soto dengan kuah segar khas Jawa.",
                R.drawable.soto,
                "Sayur",
                "Ayam Suwir - 500gr\n" +
                        "Bawang putih - 3 siung\n" +
                        "Bawang merah - 5 siung\n" +
                        "Serai - 1 batang\n" +
                        "Daun salam - 2 lembar\n" +
                        "Garam - secukupnya",
                "1. Rebus daging\n2. Tumis bumbu\n3. Campur dan sajikan"
            ),
                    Resep(
                        "Capcay",
                        "Soto dengan kuah segar khas Jawa.",
                        R.drawable.capcay,
                        "Sayur",
                        "Ayam Suwir - 500gr\n" +
                                "Bawang putih - 3 siung\n" +
                                "Bawang merah - 5 siung\n" +
                                "Serai - 1 batang\n" +
                                "Daun salam - 2 lembar\n" +
                                "Garam - secukupnya",
                        "1. Rebus daging\n2. Tumis bumbu\n3. Campur dan sajikan"
                    ),

            Resep(
                "Ayam Bakar",
                "Ayam dengan bumbu manis gurih khas Nusantara.",
                R.drawable.ayam_bakar,
                "Ayam",
                "Ayam, kecap manis, bawang putih, bawang merah, ketumbar.",
                "1. Marinasi ayam\n2. Ungkep\n3. Bakar sambil diolesi kecap"
            ),

            Resep(
                "Steak Beef",
                "Daging sapi pilihan yang juicy dan lembut.",
                R.drawable.steakbeef,
                "Daging",
                "Daging sapi, lada, mentega, bawang putih, garam.",
                "1. Panaskan wajan\n2. Masak steak\n3. Diamkan sebentar sebelum disajikan"
            )
        )

        val rvSemuaResep = view.findViewById<RecyclerView>(R.id.rv_semua_resep)
        // Perbaikan: Ganti This dengan requireContext()
        rvSemuaResep.layoutManager = GridLayoutManager(requireContext(), 2)

        semuaResepAdapter = SemuaResepAdapter(semuaResep, object : OnResepClickListener{
            override fun onResepClick(resep: Resep) {
                // Digunakan untuk mengirimkan argumen detail resep
                val action = HomeFragmentDirections.actionHomeFragmentToDetailResepFragment(
                    resep.namaResep,
                    resep.gambarResId.toString(),
                    resep.deskripsiResep,
                    resep.bahanResep,
                    resep.langkahResep,
                )
                findNavController().navigate(action)
            }
//            override fun onResepClick(resep: Resep){
//                Toast.makeText(
//                    requireContext(),
//                    "Resep Dipilih ${resep.namaResep}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        })
        rvSemuaResep.adapter = semuaResepAdapter
    } // Titik Akhir OnViewCreated

    // Fungsi Filter Kategori Resep
    private fun filterResepByKategori(Kategori: String){
        // Mencari hanya resep yang kategorinya cocok
        val filteredList =semuaResep.filter {
            it.kategori.equals(Kategori, ignoreCase = true)
        }
        // Memperbarui adapter dengan data yang sudah difilter
        semuaResepAdapter.updateData(filteredList)
    }
    // ==============================================================================================================================================
    // Fungsi untuk Indicators
    private fun setupIndicators(count: Int){
        // Hapus indikator lama (jika ada)
        indicatorsLayout.removeAllViews()

        val layoutParams: LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Memberikan jarak Antar Titik
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until count){
            // Perbaikan: sebelumnya menggunakan This sekarang menggunakan requiredContext()
            val indicators= ImageView(requireContext())
            // Perbaikan: Sebelumnya menggunakan This sekarang menggunakan requiredContext()
            indicators.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.indikator_aktif)
            )
            indicators.layoutParams=layoutParams
            indicatorsLayout.addView(indicators) // Menambahkan titik ke LinearLayout
        }
        updateIndicators(0)
    }

    private fun updateIndicators(selectedPosition: Int){
        val childCount= indicatorsLayout.childCount

        // Memberikan sintaks for dan kurung kurawal
        for (i in 0 until childCount){
            val imageView = indicatorsLayout.getChildAt(i) as ImageView

            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    // Menggunakan kondisi if/else di dalam drawable
                    if (i == selectedPosition) R.drawable.indikator_aktif
                    else R.drawable.indikator_tidakaktif
                )
            )
        }
    }

}