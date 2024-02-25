package com.example.androidsp.ui.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidsp.domain.Hero
import com.example.androidsp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(hero: Hero, modifier: Modifier){
    Card(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Column(modifier = modifier.fillMaxWidth().padding(16.dp)){
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Hero image")
            Text(text = hero.name, style = MaterialTheme.typography.titleMedium)
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
    MyCard(Hero("${it.name}", "", false), Modifier)
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
