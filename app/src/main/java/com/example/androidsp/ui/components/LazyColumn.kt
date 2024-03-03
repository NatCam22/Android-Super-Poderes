package com.example.androidsp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyLazyColumn(){
    LazyColumn{
        items(10){
            ItemDeLaLista()
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun MyLazyColumn_Preview(){
MyLazyColumn()
}

@Composable
fun ItemDeLaLista(){
    Text("Holi")
}