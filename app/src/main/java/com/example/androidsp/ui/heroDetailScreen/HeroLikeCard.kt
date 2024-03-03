package com.example.androidsp.ui.heroDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.HeroLike
import com.example.androidsp.ui.heroListScreen.HeroListViewModel
import com.example.androidsp.ui.heroListScreen.StateHeroList
import com.example.androidsp.ui.theme.PurpleGrey40
import com.example.androidsp.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch

@Composable
fun HeroLikeListScreen(viewModel: DetailScreenViewModel = hiltViewModel(), id:Int, isSerie: Boolean, navigateToSeries: (Int) -> (Unit), navigateToComics: (Int) -> (Unit), navigateToDetail: (Int) -> (Unit), navigateToHome: () -> Unit){
    if(isSerie){
        viewModel.getSeries(id)
    }else{
        viewModel.getComics(id)
    }

    val state by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopBar {
                scope.launch {scaffoldState.drawerState.open()}
            }
        },
        drawerContent = {
            CustomDrawer(
                items = DrawerItems.values().toList()){
                when(it){
                    DrawerItems.DETAIL -> {navigateToDetail(id)}
                    DrawerItems.COMICS -> {navigateToComics(id)}
                    DrawerItems.SERIES -> {navigateToSeries(id)}
                    DrawerItems.HOME -> {navigateToHome()}
                }
                scope.launch {scaffoldState.drawerState.close()}
            }
        }
    ) {
        when (state) {
            is StateHeroDetail.SuccessGetComics -> {
                HeroLikeList(
                    heros = (state as StateHeroDetail.SuccessGetComics).comics,
                    modifier = Modifier.padding(it)
                )
            }

            is StateHeroDetail.SuccessGetSeries -> {
                HeroLikeList(
                    heros = (state as StateHeroDetail.SuccessGetSeries).series,
                    modifier = Modifier.padding(it)
                )
            }

            is StateHeroDetail.Loading -> {
                LoadingScreen()
            }

            else -> {

            }
        }
    }
}

@Composable
fun HeroLikeCard(hero: HeroLike, modifier: Modifier){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
        ) {
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
            Text(text = hero.name, style = MaterialTheme.typography.body1)
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
    HeroLikeCard(Hero(111,"Goku", "", "", true), Modifier)
}

@Composable
fun HeroLikeList(heros: List<HeroLike>, modifier: Modifier){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier.background(color = PurpleGrey80).padding(16.dp)){
    items(heros){
    HeroLikeCard(Hero(it.id,it.name, it.photo, "",false), Modifier) }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeroList_Preview(){
    HeroLikeList(generateUIHeros(100), Modifier)
}

private fun generateUIHeros(size: Int) = (0 until size).map { Hero(111,"name$it",  "photo$it","",true) }
