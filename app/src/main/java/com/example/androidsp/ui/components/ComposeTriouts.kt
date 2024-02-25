package com.example.androidsp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Row1(){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier
            .size(20.dp)
            .background(Color.Blue))
        Box(modifier = Modifier
            .size(30.dp)
            .background(Color.Yellow))
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red))
    }
}

@Composable
fun Column1(){
    Column(
        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End){
        Box(modifier = Modifier
            .size(20.dp)
            .background(Color.Blue))
        Box(modifier = Modifier
            .size(30.dp)
            .background(Color.Yellow))
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red))
    }
}

@Composable
fun Row2(){
    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier
            .size(20.dp)
            .background(Color.Blue))
        Box(modifier = Modifier
            .size(20.dp)
            .background(Color.Cyan))
        Box(modifier = Modifier
            .size(20.dp)
            .background(Color.DarkGray))
    }
}
@Composable
fun Row3(){
    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
        Box(modifier = Modifier
            .background(Color.Blue)
            .height(20.dp)
            .weight(1f))
        Box(modifier = Modifier
            .background(Color.Cyan)
            .height(20.dp)
            .weight(1f))
        Box(modifier = Modifier
            .background(Color.Magenta)
            .height(20.dp)
            .weight(1f))
    }
}
@Preview
@Composable
private fun AllPreview(){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)){
        Row1()
        Column1()
        Row2()
        Row3()
    }
}