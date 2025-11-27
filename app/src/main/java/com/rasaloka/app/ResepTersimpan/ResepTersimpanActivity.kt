package com.rasaloka.app.ResepTersimpan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.ResepTersimpan.ItemResepTersimpanAdapter
import com.rasaloka.app.R

class ResepTersimpanActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemResepTersimpanAdapter
    private val listResep = mutableListOf<ResepTersimpan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resep_tersimpan)

        recyclerView = findViewById(R.id.recyclerResepTersimpan)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data — bisa diganti dari database / shared prefs
        listResep.add(
            ResepTersimpan(
                "Soto Ayam Jawa",
                "Kuah gurih dengan suwiran ayam dan rempah khas Jawa.",
                R.drawable.soto
            )
        )
        listResep.add(
            ResepTersimpan(
                "Telur Balado",
                "Telur rebus dengan sambal balado pedas menggugah selera.",
                R.drawable.telur_balado
            )
        )
        listResep.add(
            ResepTersimpan(
                "Nasi Goreng",
                "Nasi dengan bumbu gurih khas dan potongan sayur sederhana.",
                R.drawable.nasi_goreng
            )
        )

        adapter = ItemResepTersimpanAdapter(listResep) { position ->
            listResep.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.adapter = adapter
    }
}