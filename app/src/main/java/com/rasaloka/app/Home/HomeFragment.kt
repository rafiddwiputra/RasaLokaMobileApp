package com.rasaloka.app.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rasaloka.app.R
import android.text.Editable // Search bar
import android.text.TextWatcher // Search bar
import android.widget.EditText // Search bar

class HomeFragment : Fragment() {
    private lateinit var indicatorsLayout: LinearLayout
    private lateinit var resepList: List<Resep>
    private lateinit var semuaResep: List<Resep>
    private lateinit var semuaResepAdapter: SemuaResepAdapter

    // Search bar : // fungsi untuk memfilter list resep sesuai teks pencarian
    private fun filterResepBySearch(keyword: String) {

        // filter list data berdasarkan nama resep
        val filteredList = semuaResep.filter {
            it.namaResep.contains(keyword, ignoreCase = true)
        }

        // update adapter supaya recyclerview ikut berubah
        semuaResepAdapter.updateData(filteredList)
    }

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

        //ambil prefs
        val prefs = requireActivity().getSharedPreferences("USER_PREF", AppCompatActivity.MODE_PRIVATE)
        val usernameKey = prefs.getString("USERNAME", null) //lowercase key
        val usernameDisplay = prefs.getString("USERNAME_DISPLAY", usernameKey) ?: "User"
        val userPhotoRes = prefs.getInt("USER_PHOTO", R.drawable.default_user)

        // set header: image + welcome text
        val imgProfile = view.findViewById<ImageView>(R.id.gambarProfil)
        val tvWelcome = view.findViewById<TextView>(R.id.tv_welcome)

        tvWelcome.text = "Selamat Datang ${usernameDisplay.replaceFirstChar { it.uppercase() }}"
        imgProfile.setImageResource(userPhotoRes)

        //val welcomeText = view.findViewById<TextView>(R.id.tv_welcome)
        //welcomeText.text = "Selamat Datang $usernameKey"

        // Menghubungkan Data Resep Dengan ID Resep
        val viewPager: ViewPager2 =view.findViewById(R.id.view_pager_resep)
        indicatorsLayout = view.findViewById(R.id.indikator_slider)

        // Data Resep Bagian Slider
        resepList = listOf(
            Resep(
                "Steak Beef",
                "Daging sapi pilihan yang juicy dan lembut.",
                R.drawable.steakbeef,
                "Daging",
                "Daging sapi - 200 gram\n" +
                        "Mentega - 1 sdm\n" +
                        "Minyak - 1 sdm\n" +
                        "Bawang putih - 2 Siung",
                "1. Keringkan daging dengan tisu dapur agar tidak berair\n" +
                        "2. Bumbui bagian luar daging dengan garam, lada, dan bawang bubuk. Diamkan 10–15 menit\n" +
                        "3. Panaskan wajan (lebih bagus cast iron) hingga sangat panas\n" +
                        "4. Masukkan minyak, lalu letakkan daging\n" +
                        "5. Masak tanpa dibalik dulu selama 3 - 5 menit\n" +
                        "6. Tambahkan mentega, bawang putih, dan rosemar\n" +
                        "7. Siram-siram daging dengan lelehan mentega (basting) agar lebih juicy dan harum\n" +
                        "8. Angkat steak lalu istirahatkan 5 menit sebelum dipotong (biar daging tetap juicy)" ,
            ),
            Resep(
                "Capcay",
                "Capcay sederhana dengan cita rasa gurih dan segar.",
                R.drawable.capcay,
                "Sayur",
                "Bawang putih - 2 siung\n" +
                        "Bawang merah - 3 siung\n" +
                        "Wortel - 1 buah\n" +
                        "Kembang Kol - 1 bonggol\n" +
                        "Sawi Hijau - 1 genggam\n" +
                        "Kol - 1 genggam\n" +
                        "Telur - 1 butir\n" +
                        "Merica bubuk - 1/2 sdt\n" +
                        "Garam - Secukupnya\n" +
                        "Saus Tiram - 1 sdm\n" +
                        "Kecap Asin - 1/2 sdm\n" +
                        "Air - Secukupnya\n" +
                        "Minyak - Secukupnya",
                "1. Panaskan sedikit minyak,tumis bawang merah dan bawang putih sampai wangi\n" +
                        "2. Masukkan bakso, aduk rata\n" +
                        "3. Tambahkan wortel dan kembang kol, tumis sebentar\n" +
                        "4. Tuang sedikit air, lalu biarkan sayur agak empuk\n" +
                        "5. Masukkan kol dan sawi hijau\n" +
                        "6. Tambahkan merica, garam, gula, saus tiram, dan kecap asin\n" +
                        "7. Aduk rata hingga semua bahan matang dan bumbu meresap\n" +
                        "8. Angkat dan sajikan selagi hangat"
            ),
            Resep(
                "Ayam Bakar",
                "Ayam dengan bumbu manis gurih khas Nusantara.",
                R.drawable.ayam_bakar,
                "Ayam",
                "Ayam - 1 Ekor\n" +
                        "Bawang putih - 3 Siung\n" +
                        "Bawang merah - 5 Siung\n" +
                        "Kemiri - 3 Butir\n" +
                        "Ketumbar - 1 sdt\n" +
                        "Daun Salam - 2 Lembar\n" +
                        "Daun Jeruk - 2 Lembar\n" +
                        "Lengkuas - 1 Ruas\n" +
                        "Kecap Manis - 3 sdm\n" +
                        "Gula Merah - 1 sdm\n" +
                        "Garam - Secukupnya\n" +
                        "Kaldu Bubuk - Secukupnya\n" +
                        "Air - Secukupnya\n" +
                        "Minyak - Secukupnya" ,
                "1. Haluskan bumbu: bawang merah, bawang putih, kemiri, ketumbar, dan sedikit garam\n" +
                        "2. Panaskan sedikit minyak lalu tumis bumbu halus hingga harum\n" +
                        "3. Tambahkan daun salam, daun jeruk, dan lengkuas\n" +
                        "4. Masukkan potongan ayam, aduk hingga ayam berubah warna\n" +
                        "5. Tambahkan kecap manis, gula merah, dan sedikit kaldu bubuk\n" +
                        "6. Tuang air secukupnya, lalu ungkep ayam hingga bumbu meresap dan kuah menyusut\n" +
                        "7. Siapkan alat bakar (grill, teflon, arang)\n" +
                        "8. Bakar ayam sambil sesekali dioles sisa bumbu ungkep agar lebih meresap dan berwarna kecokelatan\n" +
                        "9. Masak hingga ayam matang dan permukaannya caramelized",
            )
        )

        // Menghubungkan Adapter ke ViewPager2 atau slider rekomendasi resep untuk anda
        val adapter = SliderAdapter(resepList) { resep ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailResepFragment(
                resep.namaResep,
                resep.deskripsiResep,
                resep.gambarResId,
                resep.bahanResep,
                resep.langkahResep
            )
            findNavController().navigate(action)
        }
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
            KategoriResep("Semua Resep", R.drawable.profil),
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
                "Soto Ayam Jawa",
                "Soto ayam gurih dengan kuah bening dan rempah khas Jawa.",
                R.drawable.soto,
                "Ayam",
                "Ayam suwir - 500gr\n" +
                        "Air - 1 liter\n" +
                        "Bawang putih - 3 siung\n" +
                        "Bawang merah - 5 siung\n" +
                        "Kemiri - 3 butir\n" +
                        "Kunyit - 2 cm\n" +
                        "Jahe - 2 cm\n" +
                        "Serai - 2 Batang\n" +
                        "Garam, gula, penyedap, soun, dan tauge  - Secukupnya" ,
                "1. Haluskan bawang putih, bawang merah, kemiri, kunyit, dan jahe\n" +
                        "2. Tumis bumbu halus hingga harum, tambahkan serai dan daun jeruk\n" +
                        "3. Masukkan ayam dan air rebusan, didihkan hingga bumbu meresap\n" +
                        "4. Koreksi rasa dengan garam, gula, dan penyedap\n" +
                        "5. Sajikan ayam suwir dengan kuah panas, soun, tauge, telur rebus, dan taburan bawang goreng",
            ),

            Resep(
                "Ayam Bakar",
                "Ayam dengan bumbu manis gurih khas Nusantara.",
                R.drawable.ayam_bakar,
                "Ayam",
                "Ayam - 1 Ekor\n" +
                        "Bawang putih - 3 Siung\n" +
                        "Bawang merah - 5 Siung\n" +
                        "Kemiri - 3 Butir\n" +
                        "Ketumbar - 1 sdt\n" +
                        "Daun Salam - 2 Lembar\n" +
                        "Daun Jeruk - 2 Lembar\n" +
                        "Lengkuas - 1 Ruas\n" +
                        "Kecap Manis - 3 sdm\n" +
                        "Gula Merah - 1 sdm\n" +
                        "Kaldu Bubuk - Secukupnya\n" +
                        "Air - Secukupnya\n" +
                        "Minyak - Secukupnya" ,
                "1. Haluskan bumbu: bawang merah, bawang putih, kemiri, ketumbar, dan sedikit garam\n" +
                        "2. Panaskan sedikit minyak lalu tumis bumbu halus hingga harum\n" +
                        "3. Tambahkan daun salam, daun jeruk, dan lengkuas\n" +
                        "4. Masukkan potongan ayam, aduk hingga ayam berubah warna\n" +
                        "5. Tambahkan kecap manis, gula merah, dan sedikit kaldu bubuk\n" +
                        "6. Tuang air secukupnya, lalu ungkep ayam hingga bumbu meresap dan kuah menyusut\n" +
                        "7. Siapkan alat bakar (grill, teflon, arang)\n" +
                        "8. Bakar ayam sambil sesekali dioles sisa bumbu ungkep agar lebih meresap dan berwarna kecokelatan\n" +
                        "9. Masak hingga ayam matang dan permukaannya caramelized",
            ),

            Resep(
                "Opor Ayam",
                "Ayam berkuah santan lembut dengan bumbu kuning.",
                R.drawable.opor_ayam,
                "Ayam",
                "Ayam - 400 gram\n" +
                        "Santan - 200 ml\n" +
                        "Serai - 1 batang\n" +
                        "Daun salam - 2 lembar\n" +
                        "Kunyit - 2 cm\n" +
                        "Bawang putih - 4 siung\n" +
                        "Bawang merah - 5 siung\n" +
                        "Garam - 1 sdt\n" +
                        "Minyak - 1 sdm",
                "1. Haluskan bawang putih, bawang merah, dan kunyit\n" +
                        "2. Tumis bumbu halus hingga harum\n" +
                        "3. Masukkan ayam lalu aduk sampai berubah warna\n" +
                        "4. Tambahkan santan, serai, dan daun salam\n" +
                        "5. Masak dengan api kecil sambil diaduk agar santan tidak pecah\n" +
                        "6. Bumbui dengan garam dan masak hingga ayam empuk",
            ),

            Resep(
                "Sate Ayam",
                "Ayam panggang tusuk dengan bumbu kacang gurih.",
                R.drawable.sate_ayam,
                "Ayam",
                "Dada ayam - 300 gram\n" +
                        "Kecap manis - 3 sdm\n" +
                        "Bawang putih - 3 siung\n" +
                        "Merica - 1/2 sdt\n" +
                        "Tusuk sate - secukupnya\n" +
                        "Kacang tanah goreng - 100 gram\n" +
                        "Cabe rawit - 3 buah\n" +
                        "Garam - 1/2 sdt\n" +
                        "Minyak - 1 sdm",
                "1. Potong ayam kecil lalu marinasi dengan kecap, bawang putih, dan lada\n" +
                        "2. Tusukkan ayam pada tusuk sate\n" +
                        "3. Haluskan kacang goreng dengan cabe, garam, dan sedikit air\n" +
                        "4. Panggang sate sambil dioles kecap\n" +
                        "5. Sajikan sate dengan saus kacang",
            ),

            Resep(
                "Steak Beef",
                "Daging sapi pilihan yang juicy dan lembut.",
                R.drawable.steakbeef,
                "Daging",
                "Daging sapi - 200 gram\n" +
                        "Garam - 1 sdt\n" +
                        "Mentega - 1 sdm\n" +
                        "Minyak - 1 sdm\n" +
                        "Bawang putih - 2 Siung",
                "1. Keringkan daging dengan tisu dapur agar tidak berair\n" +
                        "2. Bumbui bagian luar daging dengan garam, lada, dan bawang bubuk. Diamkan 10–15 menit\n" +
                        "3. Panaskan wajan (lebih bagus cast iron) hingga sangat panas\n" +
                        "4. Masukkan minyak, lalu letakkan daging\n" +
                        "5. Masak tanpa dibalik dulu selama 3 - 5 menit\n" +
                        "6. Tambahkan mentega, bawang putih, dan rosemar\n" +
                        "7. Siram-siram daging dengan lelehan mentega (basting) agar lebih juicy dan harum\n" +
                        "8. Angkat steak lalu istirahatkan 5 menit sebelum dipotong (biar daging tetap juicy)" ,
            ),

            Resep(
                "Rendang",
                "Daging yang dimasak perlahan dengan santan dan rempah hingga kental dan meresap.",
                R.drawable.rendang,
                "Daging",
                "Daging sapi - 500 gr\n" +
                        "Santan - 500 ml\n" +
                        "Bawang putih - 5 siung\n" +
                        "Bawang merah - 7 siung\n" +
                        "Cabai merah - 7 buah\n" +
                        "Daun jeruk - 2 lembar\n" +
                        "Serai - 2 batang\n" +
                        "Garam - 1 sdt",
                "1. Haluskan bawang, cabai, dan rempah\n" +
                        "2. Tumis hingga harum lalu masukkan daging\n" +
                        "3. Tambahkan santan dan aduk rata\n" +
                        "4. Masukkan daun jeruk, serai, dan garam\n" +
                        "5. Masak dengan api kecil sambil sesekali diaduk\n" +
                        "6. Masak sampai santan mengental dan bumbu meresap"
            ),

            Resep(
                "Semur Daging",
                "Daging sapi empuk dibumbui kecap manis dan rempah khas Indonesia.",
                R.drawable.semur_daging,
                "Daging",
                "Daging sapi - 400 gr\n" +
                        "Kecap manis - 6 sdm\n" +
                        "Bawang merah - 5 siung\n" +
                        "Bawang putih - 3 siung\n" +
                        "Pala - 1/2 buah\n" +
                        "Cengkeh - 3 butir\n" +
                        "Garam - 1 sdt",
                "1. Tumis bawang hingga harum\n" +
                        "2. Masukkan daging dan aduk rata\n" +
                        "3. Tambahkan kecap dan air secukupnya\n" +
                        "4. Masukkan pala dan cengkeh\n" +
                        "5. Masak hingga daging empuk\n" +
                        "6. Tunggu sampai kuah mengental"
            ),

            Resep (
                "Rawon",
                "Kuah hitam khas Jawa Timur dengan bumbu kluwek.",
                R.drawable.rawon,
                "Daging",
                "Daging sapi - 400 gr\n" +
                        "Kluwek - 4 butir\n" +
                        "Bawang merah - 5 siung\n" +
                        "Bawang putih - 3 siung\n" +
                        "Lengkuas - 2 cm\n" +
                        "Daun jeruk - 2 lembar\n" +
                        "Garam - 1 sdt",

                "1. Haluskan kluwek dan bawang\n" +
                        "2. Tumis sampai wangi\n" +
                        "3. Rebus daging hingga empuk\n" +
                        "4. Masukkan bumbu ke dalam kuah rebusan\n" +
                        "5. Tambahkan lengkuas dan daun jeruk\n" +
                        "6. Masak hingga kuah hitam dan pekat"
            ),

            Resep(
                "Capcay",
                "Capcay sederhana dengan cita rasa gurih dan segar.",
                R.drawable.capcay,
                "Sayur",
                "Bawang putih - 2 siung\n" +
                        "Bawang merah - 3 siung\n" +
                        "Kembang Kol - 1 bonggol\n" +
                        "Sawi Hijau - 1 genggam\n" +
                        "Kol - 1 genggam\n" +
                        "Telur - 1 butir\n" +
                        "Merica bubuk - 1/2 sdt\n" +
                        "Saus Tiram - 1 sdm\n" +
                        "Kecap Asin - 1/2 sdm\n" +
                        "Air - Secukupnya\n" +
                        "Minyak - Secukupnya",
                "1. Panaskan sedikit minyak,tumis bawang merah dan bawang putih sampai wangi\n" +
                        "2. Masukkan bakso, aduk rata\n" +
                        "3. Tambahkan wortel dan kembang kol, tumis sebentar\n" +
                        "4. Tuang sedikit air, lalu biarkan sayur agak empuk\n" +
                        "5. Masukkan kol dan sawi hijau\n" +
                        "6. Tambahkan merica, garam, gula, saus tiram, dan kecap asin\n" +
                        "7. Aduk rata hingga semua bahan matang dan bumbu meresap\n" +
                        "8. Angkat dan sajikan selagi hangat"
            ),

            Resep(
                "Sayur Sop",
                "Sayur kuah gurih berisi wortel dan kentang.",
                R.drawable.sayur_sop,
                "Sayur",
                "Wortel - 1 buah\n" +
                        "Kentang - 1 buah\n" +
                        "Kol - 50 gram\n" +
                        "Bawang putih - 3 siung\n" +
                        "Merica - 1/4 sdt\n" +
                        "Garam - 1 sdt\n" +
                        "Air - 700 ml",
                "1. Didihkan air lalu masukkan bawang putih\n" +
                        "2. Tambahkan wortel dan kentang hingga agak empuk\n" +
                        "3. Masukkan kubis, garam, dan lada\n" +
                        "4. Masak hingga semua sayur lunak\n" +
                        "5. Sajikan hangat",
            ),

            Resep(
                "Tumis Kangkung",
                "Kangkung tumis bawang putih gurih dan sederhana.",
                R.drawable.tumis_kangkung,
                "Sayur",
                "Kangkung - 1 ikat\n" +
                        "Bawang putih - 4 siung\n" +
                        "Cabai merah - 3 buah\n" +
                        "Garam - 1 sdt\n" +
                        "Minyak - 2 sdm",
                "1. Panaskan minyak lalu tumis bawang putih dan cabai\n" +
                        "2. Masukkan kangkung dan aduk cepat\n" +
                        "3. Tambahkan garam dan masak sebentar\n" +
                        "4. Sajikan segera agar tetap renyah",
            ),

            Resep(
                "Sayur Lodeh",
                "Olahan sayur santan dengan bumbu khas Jawa.",
                R.drawable.sayur_lodeh,
                "Sayur",
                "Terong - 1 buah\n" +
                        "Kacang panjang - 100 gram\n" +
                        "Santan - 200 ml\n" +
                        "Cabai merah - 3 buah\n" +
                        "Bawang merah - 5 siung\n" +
                        "Bawang putih - 3 siung\n" +
                        "Garam - 1 sdt",
                "1. Tumis bawang merah, bawang putih, dan cabai hingga harum\n" +
                        "2. Tambahkan sayuran dan aduk rata\n" +
                        "3. Tuang santan lalu bumbui garam\n" +
                        "4. Masak hingga sayur empuk\n" +
                        "5. Sajikan saat hangat",
            ),

            Resep(
                "Omelette",
                "Telur dadar lembut dengan campuran sayuran.",
                R.drawable.omelette,
                "Telur",
                "Telur ayam - 2 butir\n" +
                        "Daun bawang - 1 batang\n" +
                        "Garam - 1/4 sdt\n" +
                        "Merica - 1/4 sdt\n" +
                        "Mentega - 1 sdm",
                "1. Kocok telur dengan garam dan lada\n" +
                        "2. Tambahkan daun bawang\n" +
                        "3. Panaskan mentega di wajan\n" +
                        "4. Tuang telur dan masak dengan api kecil\n" +
                        "5. Lipat omelette lalu angkat",
            ),

            Resep(
                "Telur Balado",
                "Telur rebus dengan sambal balado pedas.",
                R.drawable.telur_balado,
                "Telur",
                "Telur ayam - 4 butir\n" +
                        "Cabai merah besar - 5 buah\n" +
                        "Bawang merah - 5 siung\n" +
                        "Bawang putih - 3 siung\n" +
                        "Garam - 1 sdt\n" +
                        "Minyak - 3 sdm",
                "1. Rebus telur hingga matang lalu kupas\n" +
                        "2. Haluskan cabai, bawang merah, dan bawang putih\n" +
                        "3. Tumis bumbu hingga harum\n" +
                        "4. Masukkan telur dan aduk rata\n" +
                        "5. Masak sebentar hingga bumbu menyerap",
            ),

            Resep(
                "Telur Dadar Padang",
                "Versi tebal berbumbu khas Minang.",
                R.drawable.telur_dadar_padang,
                "Telur",
                "Telur ayam - 4 butir\n" +
                        "Daun bawang - 1 batang\n" +
                        "Cabai merah - 3 buah\n" +
                        "Tepung beras - 1 sdm\n" +
                        "Garam - 1/2 sdt\n" +
                        "Minyak - 3 sdm",
                "1. Kocok telur dengan garam\n" +
                        "2. Masukkan daun bawang, cabai, dan tepung beras\n" +
                        "3. Panaskan minyak cukup banyak\n" +
                        "4. Tuang adonan dan masak hingga bagian luar kecokelatan\n" +
                        "5. Balik perlahan lalu masak hingga matang",
            ),

            Resep(
                "Sup Telur",
                "Kuah hangat dengan telur orak-arik lembut.",
                R.drawable.sup_telur,
                "Telur",
                "Telur ayam - 2 butir\n" +
                        "Bawang putih - 2 siung\n" +
                        "Daun bawang - 1 batang\n" +
                        "Garam - 1 sdt\n" +
                        "Air - 600 ml",
                "1. Didihkan air lalu masukkan bawang putih\n" +
                        "2. Kocok telur dan tuang perlahan sambil diaduk\n" +
                        "3. Tambahkan garam\n" +
                        "4. Masukkan daun bawang\n" +
                        "5. Sajikan hangat",
            )

        )

        //Search bar : mengambil edit text search dari layout
        val searchBar = view.findViewById<EditText>(R.id.edit_text_search)

        // listener untuk mendeteksi teks berubah saat mengetik
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // panggil fungsi filter
                filterResepBySearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val rvSemuaResep = view.findViewById<RecyclerView>(R.id.rv_semua_resep)
        // Perbaikan: Ganti This dengan requireContext()
        rvSemuaResep.layoutManager = GridLayoutManager(requireContext(), 2)

        semuaResepAdapter = SemuaResepAdapter(semuaResep, object : OnResepClickListener{
            override fun onResepClick(resep: Resep) {
                // Digunakan untuk mengirimkan argumen detail resep
                val action = HomeFragmentDirections.actionHomeFragmentToDetailResepFragment(
                    resep.namaResep,
                    resep.deskripsiResep,
                    resep.gambarResId,
                    resep.bahanResep,
                    resep.langkahResep
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