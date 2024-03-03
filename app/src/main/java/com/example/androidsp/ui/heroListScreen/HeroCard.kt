package com.example.androidsp.ui.heroListScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.androidsp.domain.Hero
import com.example.androidsp.ui.theme.PurpleGrey40

@Composable
fun HeroListScreen(viewModel: HeroListViewModel = hiltViewModel(), navigateToDetail: (Int) -> Unit){
    val state by viewModel.uiState.collectAsState()
    when(state){
        is StateHeroList.SuccessGetHeros-> {
            println((state as StateHeroList.SuccessGetHeros).heroList[2])
            HeroList(heros = (state as StateHeroList.SuccessGetHeros).heroList, modifier = Modifier, navigateToDetail){
                viewModel.setHeroFav(it)
            }
        }
        else -> {

        }
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier, navigateToDetail: (Int) -> (Unit), onFavClicked: (Hero) -> Unit){
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
            Row( verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = hero.name, style = MaterialTheme.typography.h5)
                //Atencion: Componente no visto en clase. El IconToggleButton como su nombre lo indica, nos permite tener un boton de dos estados (como si fuera un
                //switch o un checkbox, pero con la libertad de que el cambio se realiza sobre el contenido y es personalizable.
                //Es ideal para los botones que al presionarse deben cambiar de apariencia y en particular los que cambian de icono con el click.
                //Este caso es un caso de uso tipico para el componente ya que es un IconButton con icono dependiente de si el boton esta "checked" o no.

                IconToggleButton(checked = hero.favorite, onCheckedChange = {
                    onFavClicked(hero)
                }) {
                    Icon(imageVector = (if(hero.favorite) Icons.Default.Star else Icons.Default.StarBorder), contentDescription = "Favourite Icon", modifier = Modifier.height(30.dp).width(30.dp).testTag("favIcon"))
                }

            }
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
    HeroCard(Hero(111,"Goku", "", "", true), Modifier, {}){

    }
}
@Composable
fun HeroList(heros: List<Hero>, modifier: Modifier, navigateToDetail: (Int) -> Unit, onFavClicked: (Hero) -> Unit){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier
        .background(color = PurpleGrey40)
        .padding(16.dp)){
    items(heros){
    HeroCard(it, Modifier, navigateToDetail, onFavClicked) }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeroList_Preview(){
    HeroList(generateUIHeros(100), Modifier, {}){}
}

private fun generateUIHeros(size: Int) = (0 until size).map { Hero(111,"name$it",  "photo$it","",true) }
