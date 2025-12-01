package com.rasaloka.app.Home

data class Resep (
    val namaResep: String,
    val deskripsiResep: String,
    val gambarResId: Int,
    val kategori: String,
    // Data DetailResep
    val bahanResep: String,
    val langkahResep: String
)