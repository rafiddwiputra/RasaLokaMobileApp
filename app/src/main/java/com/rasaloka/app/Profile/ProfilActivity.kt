package com.rasaloka.app.Profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rasaloka.app.R

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        // --- Ambil komponen dari XML ---
        val imgProfile: ImageView = findViewById(R.id.imgProfile)
        val txtGreeting: TextView = findViewById(R.id.txtGreeting)
        val inputUsername: EditText = findViewById(R.id.inputUsername)
        val inputPassword: EditText = findViewById(R.id.inputPassword)
        val btnLogout: Button = findViewById(R.id.btnLogout)

        val navHome: TextView = findViewById(R.id.navHome)
        val navSaved: TextView = findViewById(R.id.navSaved)
        val navProfile: TextView = findViewById(R.id.navProfile)

        // --- Set data statis sementara ---
        txtGreeting.text = "Hii!! Putraa"
        inputUsername.setText("Putraa")
        inputPassword.setText("********")
        imgProfile.setImageResource(R.drawable.ic_profile)

        // --- Tombol Logout ---
        btnLogout.setOnClickListener {
            // Nanti bisa diarahkan ke LoginActivity
            // startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // --- Navigasi Bottom ---
        navHome.setOnClickListener {
            // nanti diarahkan ke HomeActivity
            // startActivity(Intent(this, HomeActivity::class.java))
        }

        navSaved.setOnClickListener {
            // nanti diarahkan ke SavedActivity
            // startActivity(Intent(this, SavedActivity::class.java))
        }

        navProfile.setOnClickListener {
            // kamu sudah di halaman ini, jadi tidak perlu berpindah
        }
    }
}