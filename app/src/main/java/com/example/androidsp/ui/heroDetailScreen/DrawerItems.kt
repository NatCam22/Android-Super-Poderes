package com.example.androidsp.ui.heroDetailScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerItems (
    val icon: ImageVector,
    val text: String
) {
    SERIES(Icons.Rounded.Tv, "Series"),
    COMICS(Icons.Rounded.Book, "Comics"),
    DETAIL(Icons.Rounded.Search, "Detalle")
}