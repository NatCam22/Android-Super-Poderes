package com.example.androidsp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(){
    Scaffold(
        topBar = {
            MyTopBar()
        }
    ) {

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Cyan)
                .padding(it))
    }
}

@Composable
fun MyTopBar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        Text("Mi scaffold")
        Icon(imageVector = Icons.Filled.Menu, contentDescription = "dropdown")
    }
}

@Preview(showBackground = true)
@Composable
private fun MyTopBar_Preview(){
    MyTopBar()
}

@Preview(showSystemUi = true)
@Composable
private fun MyScaffold_Preview(){
    MyScaffold()
}