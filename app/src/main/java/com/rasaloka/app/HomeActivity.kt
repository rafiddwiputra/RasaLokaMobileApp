package com.rasaloka.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Digunakan untuk memuat layout activity_home yang berisikan NavHostFragment dan BottomNavigationView
        setContentView(R.layout.activity_home)

        // Dipakai oleh fragment
        val prefs = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val username = prefs.getString("USERNAME", null)

        // Mendapatkan NavHostFragment sebagai tempat wadah utama
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Mendapatkan NavController dari NavHostFragment
        val navController=navHostFragment.navController

        // Mendapatkan BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Menghubungkan BottomNavigationView dengan NavController
        // kode baris ini akan secara otomatis menangani klik menu dan navigasi antar fragment
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        if (!navController.popBackStack()) {
            // Back stack kosong → keluar dari app
            super.onBackPressed()
        }
    }

}
