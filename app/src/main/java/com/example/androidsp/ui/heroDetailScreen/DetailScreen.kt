package com.example.androidsp.ui.heroDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.androidsp.domain.Hero
import kotlinx.coroutines.launch


@Composable
fun LoadingScreen(){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
    }
}

@Composable
fun HeroDetailScreen(viewModel: DetailScreenViewModel = hiltViewModel(), id: Int, navigateToSeries: (Int) -> (Unit), navigateToComics: (Int) -> (Unit), navigateToDetail: (Int) -> (Unit)){
    viewModel.getHero(id)
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
                }
                scope.launch {scaffoldState.drawerState.close()}
            }
        }
        ){
        when(state){
            is StateHeroDetail.SuccessGetHero-> {
                println((state as StateHeroDetail.SuccessGetHero).hero.photo)
                ImageScreen(hero = (state as StateHeroDetail.SuccessGetHero).hero, modifier = Modifier.padding(it))
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
fun TextShadow(textInput: String) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = textInput,
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Red, offset = offset, blurRadius = 3f
            )
        ),
        color = Color.White
    )
}
@Composable
fun DescriptionBox(name: String, description: String, modifier: Modifier){
    Box(
        modifier = modifier
            .background(color = Color("#50ff3000".toColorInt()))
            .fillMaxWidth()
            .height(550.dp)
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TextShadow(textInput = name)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                textAlign = TextAlign.Justify)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DescriptionBox_Preview(){
    DescriptionBox(name = "Scarlet Witch", description = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
        modifier = Modifier)
}

@Composable
fun ImageScreen(hero: Hero, modifier: Modifier){
    var loading by remember{
        mutableStateOf(true)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ){
        Image(
            painter = rememberAsyncImagePainter(hero.photo,
                onState = {
                    loading = it is AsyncImagePainter.State.Loading
                }),
            contentDescription = "Hero image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
        DescriptionBox(name = hero.name, description = hero.description, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showSystemUi = true)
@Composable
fun ImageScreen_Preview(){
    val hero = Hero(111, "Goku", "", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", false)
    ImageScreen(hero = hero, modifier = Modifier)
}