package com.rasaloka.app.Profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rasaloka.app.Login.LoginActivity
import com.rasaloka.app.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ambil data user dari sharedPreferences
        val prefs =
            requireActivity().getSharedPreferences("USER_PREF", AppCompatActivity.MODE_PRIVATE)

        val usernameDisplay = prefs.getString("USERNAME_DISPLAY", "User")
        val username = prefs.getString("USERNAME", "user")
        val userPhotoRes = prefs.getInt("USER_PHOTO", R.drawable.default_user)

        // Mengambil komponen dari XML
        val imgProfile: ImageView = view.findViewById(R.id.imgProfile)
        val txtGreeting: TextView = view.findViewById(R.id.txtGreeting)
        val inputUsername: EditText = view.findViewById(R.id.inputUsername)
        val inputPassword: EditText = view.findViewById(R.id.inputPassword)
        val btnLogOut: Button = view.findViewById(R.id.btnLogout)

        // Setting Data Statis Sementara
        //val prefs = requireActivity().getSharedPreferences("USER_PREF", AppCompatActivity.MODE_PRIVATE)

        //val username = prefs.getString("USERNAME", "User")

        // set data from prefs
        txtGreeting.text = "Hai!! ${usernameDisplay?.replaceFirstChar { it.uppercase() }}"
        inputUsername.setText(usernameDisplay)

        // jangan simpan password nyata di SharedPreferences kecuali terenkripsi; ini hanya placeholder
        inputPassword.setText("***") // placeholder
        imgProfile.setImageResource(userPhotoRes)

        // Tombol Layout (Logika Logout di Fragment)
        btnLogOut.setOnClickListener {
            // clear prefs jika mau logout
            prefs.edit().clear().apply()

            val edit = prefs.edit()
            edit.remove("USERNAME")
            edit.remove("USERNAME_DISPLAY")
            edit.remove("USER_PHOTO")
            edit.apply()

            //val prefs = requireActivity()
            //.getSharedPreferences("USER_PREF", AppCompatActivity.MODE_PRIVATE)

            //prefs.edit().clear().apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}




// Dalam sistem fragmen, ketika kita ingin berpindah ke Activity lain (misal: LoginActivity.kt)
// Kita harus mendapatkan cotext activity
// ATAU, jika LoginActivity adalah start destination, kita bisa menggunakan NavController
// untuk pindah (jika Login di-host oleh Activity yang sama).

// Untuk tujuan migrasi, kita pertahankan logika dasarnya:
// Jika Anda ingin kembali ke HomeActivity dan menutupnya (simulasi logout):

//activity?.finish()
// Jika Anda ingin pindah ke LoginActivity (dan LoginActivity sudah ada):
// val intent = Intent(requireContext(), LoginActivity::class.java)
// startActivity(intent)
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