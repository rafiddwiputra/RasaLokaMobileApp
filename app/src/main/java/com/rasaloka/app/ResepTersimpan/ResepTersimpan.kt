package com.rasaloka.app.ResepTersimpan

data class ResepTersimpan(
    val title: String,
    val description: String,
    val image: Int,
    val bahan: String = "",
    val langkah: String = ""
)