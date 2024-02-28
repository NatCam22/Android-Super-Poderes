package com.example.androidsp.ui.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.androidsp.domain.Hero
import com.example.androidsp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(hero: Hero, modifier: Modifier){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        var loading by remember{
            mutableStateOf(true)
        }
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)){
            Image(
                painter = rememberAsyncImagePainter(hero.photo,
                    onState = {
                        loading = it is AsyncImagePainter.State.Loading
                    }),
                contentDescription = "Hero image",
                modifier = Modifier.height(200.dp).fillMaxWidth())
            Text(text = hero.name, style = MaterialTheme.typography.titleMedium)
        }
        if(loading){
            Box(modifier = Modifier.height(200.dp).fillMaxWidth()){
                CircularProgressIndicator(modifier = modifier.align(androidx.compose.ui.Alignment.Center))
            }
        }
    }
}
@Preview
@Composable
fun MyCard_Preview(){
    MyCard(Hero("Goku", "", true), Modifier)
}
@Composable
fun HeroList(heros: List<Hero>, modifier: Modifier){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier.padding(16.dp)){
    items(heros){
    MyCard(Hero("${it.name}", "${it.photo}", false), Modifier)
}
    }
}

@Preview(showBackground = true)
@Composable
private fun HeroList_Preview(){
    HeroList(generateUIHeros(100), Modifier)
}

@Preview(showSystemUi = true)
@Composable
private fun MyScaffold_Preview(){
    MyScaffold()
}

private fun generateUIHeros(size: Int) = (0 until size).map { Hero("name$it",  "photo$it",true) }
