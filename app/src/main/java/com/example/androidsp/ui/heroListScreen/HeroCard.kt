package com.example.androidsp.ui.heroListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.HeroLike

@Composable
fun HeroListScreen(viewModel: HeroListViewModel = hiltViewModel(), navigateToDetail: (Int) -> Unit){
    val state by viewModel.uiState.collectAsState()
    when(state){
        is StateHeroList.SuccessGetHeros-> {
            println((state as StateHeroList.SuccessGetHeros).heroList[0].photo)
            HeroList(heros = (state as StateHeroList.SuccessGetHeros).heroList, modifier = Modifier, navigateToDetail)
        }
        else -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroCard(hero: HeroLike, modifier: Modifier, navigateToDetail: (Int) -> (Unit)){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { navigateToDetail(hero.id) }) {
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
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth())
            Text(text = hero.name, style = MaterialTheme.typography.titleMedium)
        }
        if(loading){
            Box(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()){
                CircularProgressIndicator(modifier = modifier.align(androidx.compose.ui.Alignment.Center))
            }
        }
    }
}
@Preview
@Composable
fun MyCard_Preview(){
    HeroCard(Hero(111,"Goku", "", "", true), Modifier, ){

    }
}
@Composable
fun HeroList(heros: List<HeroLike>, modifier: Modifier, navigateToDetail: (Int) -> Unit){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier.padding(16.dp)){
    items(heros){
    HeroCard(Hero(it.id,"${it.name}", "${it.photo}", "",false), Modifier, navigateToDetail) }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeroList_Preview(){
    HeroList(generateUIHeros(100), Modifier, {})
}

private fun generateUIHeros(size: Int) = (0 until size).map { Hero(111,"name$it",  "photo$it","",true) }
