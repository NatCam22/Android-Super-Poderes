package com.example.androidsp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun MyAsyncImage(url: String){
    AsyncImage(
        model = url,
        contentDescription = "Image from url-perritos"
    )
}

@Preview
@Composable
fun MyAsyncImage_Preview(){
    MyAsyncImage("https://st2.depositphotos.com/3378121/5193/i/450/depositphotos_51933801-stock-photo-labrador-puppies-in-a-basket.jpg")
}

@Composable
fun MyasyncImage2(url: String){
    Image(
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = "Imagen de perritos"
    )
}

@Preview
@Composable
fun MyAsyncImage2_Preview(){
    MyAsyncImage(url = "https://st2.depositphotos.com/3378121/5193/i/450/depositphotos_51933801-stock-photo-labrador-puppies-in-a-basket.jpg")
}