package com.rasaloka.app.ResepTersimpan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rasaloka.app.R
import androidx.navigation.fragment.findNavController

class ResepTersimpanFragment : Fragment() {

    private val listResep = mutableListOf<ResepTersimpan>()
    private lateinit var adapter: ItemResepTersimpanAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resep_tersimpan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerResepTersimpan)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadBookmarks()

        adapter = ItemResepTersimpanAdapter(
            listResep,
            onDelete = { position ->
                val prefs = requireContext().getSharedPreferences("BOOKMARKS", 0)
                val resepName = listResep[position].title

                prefs.edit()
                    .remove("${resepName}_saved")
                    .remove("${resepName}_desc")
                    .remove("${resepName}_img")
                    .remove("${resepName}_bahan")
                    .remove("${resepName}_langkah")
                    .apply()

                listResep.removeAt(position)
                adapter.notifyItemRemoved(position)
            },
            onClick = { resep ->   // ← Pindah ke Detail
                val action = ResepTersimpanFragmentDirections.actionResepTersimpanToDetailResep(
                    resep.title,
                    resep.description,
                    resep.image,
                    resep.bahan,
                    resep.langkah
                )
                findNavController().navigate(action)
            }
        )

        recyclerView.adapter = adapter
    }

    private fun loadBookmarks() {
        listResep.clear()
        val prefs = requireContext().getSharedPreferences("BOOKMARKS", 0)

        prefs.all.forEach { entry ->
            if (entry.key.endsWith("_saved") && entry.value == true) {

                val nama = entry.key.removeSuffix("_saved")

                val desc = prefs.getString("${nama}_desc", "") ?: ""
                val img = prefs.getInt("${nama}_img", R.drawable.capcay)
                val bahan = prefs.getString("${nama}_bahan", "") ?: ""
                val langkah = prefs.getString("${nama}_langkah", "") ?: ""

                listResep.add(ResepTersimpan(nama, desc, img, bahan, langkah))
            }
        }
    }


    override fun onResume() {
        super.onResume()
        loadBookmarks()
        if (this::adapter.isInitialized) adapter.notifyDataSetChanged()
    }
}
