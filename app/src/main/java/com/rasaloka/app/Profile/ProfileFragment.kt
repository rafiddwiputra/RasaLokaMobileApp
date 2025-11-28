package com.rasaloka.app.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.rasaloka.app.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    // Tambahkan Kode Atau Salin Kode Yang Ada Di File ProfileActivity.kt dan Tempelkan Di sini.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil komponen dari XML
        val imgProfile: ImageView=view.findViewById(R.id.imgProfile)
        val txtGreeting: TextView=view.findViewById(R.id.txtGreeting)
        val inputUsername: EditText=view.findViewById(R.id.inputUsername)
        val inputPassword: EditText=view.findViewById(R.id.inputPassword)
        val btnLogOut: Button=view.findViewById(R.id.btnLogout)

        // Setting Data Statis Sementara
        txtGreeting.text = "Hi!! Putraa"
        inputUsername.setText("Putraa")
        inputPassword.setText("********")
        imgProfile.setImageResource(R.drawable.ic_profile)

        // Tombol Layout (LogikaLogoutdi Fragment
        btnLogOut.setOnClickListener {
            // Dalam sistem fragmen, ketika kita ingin berpindah ke Activity lain (misal: LoginActivity.kt)
            // Kita harus mendapatkan cotext activity
            // ATAU, jika LoginActivity adalah start destination, kita bisa menggunakan NavController
            // untuk pindah (jika Login di-host oleh Activity yang sama).

            // Untuk tujuan migrasi, kita pertahankan logika dasarnya:
            // Jika Anda ingin kembali ke HomeActivity dan menutupnya (simulasi logout):
            activity?.finish()
            // Jika Anda ingin pindah ke LoginActivity (dan LoginActivity sudah ada):
            // val intent = Intent(requireContext(), LoginActivity::class.java)
            // startActivity(intent)
        }
        /*
       // --- Navigasi Bottom (Bagian ini TIDAK DIPERLUKAN LAGI!) ---
       // Karena kita menggunakan setupWithNavController di HomeActivity,
       // Tombol navigasi BUKAN LAGI TANGGUNG JAWAB Fragment.
       // Bagian ini dapat DIBUANG dari ProfilFragment dan layout XML-nya (activity_profil.xml lama).
       navHome.setOnClickListener {
           // Logika ini sekarang ditangani oleh HomeActivity.kt
       }
       navSaved.setOnClickListener {
           // Logika ini sekarang ditangani oleh HomeActivity.kt
       }
       navProfile.setOnClickListener {
           // Sudah di sini, tidak perlu berpindah
       }
       */
    }
}