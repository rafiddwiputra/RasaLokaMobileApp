package com.rasaloka.app.ResepTersimpan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R

class ResepTersimpanFragment : Fragment() {

    private val listResep =mutableListOf<ResepTersimpan>()
    private lateinit var adapter: ItemResepTersimpanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resep_tersimpan, container, false)
    }
    // Tambahkan Kode Atau Salin Kode Yang Ada Di File ResepTersimpanActivity.kt dan Tempelkan Di sini.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerResepTersimpan)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (listResep.isEmpty()){
            listResep.add(
                ResepTersimpan(
                    "Soto Ayam Jawa ",
                    "Kuah Gurih Dengan Suwiran Ayam Dan Rempah Khas Jawa",
                    R.drawable.soto
                )
            )
            listResep.add(
                ResepTersimpan(
                    "Telur Balado",
                    "Telur Rebus Dengan Sambal Balado Pedas Mengugah Selera",
                    R.drawable.telur_balado
                )
            )
            listResep.add(
                ResepTersimpan(
                    "Nasi Goreng",
                    "Nasi Dengan Bumbu Gurih Khas Dan Potongan Sayur Sederhana",
                    R.drawable.nasi_goreng
                )
            )
        }

        adapter= ItemResepTersimpanAdapter(listResep){position ->
            listResep.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        recyclerView.adapter = adapter
    }
}