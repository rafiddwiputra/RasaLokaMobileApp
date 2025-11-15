package com.rasaloka.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class HomeActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerResep)

        val daftarResep = listOf(
            Resep(
                namaMakanan = "Steak Beef",
                deskripsiMakanan = "Steak lezat dengan saus creamy.",
                imageResId = R.drawable.steakbeef
            ),
            Resep(
                namaMakanan = "Smoothie Bowl",
                deskripsiMakanan = "Buah segar dengan topping granola.",
                imageResId = R.drawable.kemiri
            )
        )

        val adapter = ResepAdapter(daftarResep)
        viewPager.adapter = adapter

        handler = Handler(Looper.getMainLooper())

        val runnable = object : Runnable {
            override fun run() {
                if (adapter.itemCount == 0) return
                currentPage = (currentPage + 1) % adapter.itemCount
                viewPager.currentItem = currentPage
                handler.postDelayed(this, 3000)
            }
        }

        handler.postDelayed(runnable, 3000)
    }
}
