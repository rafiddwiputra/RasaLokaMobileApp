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
    }
}