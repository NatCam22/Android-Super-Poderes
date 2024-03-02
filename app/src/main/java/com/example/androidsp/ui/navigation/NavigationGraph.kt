package com.example.androidsp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidsp.ui.heroDetailScreen.HeroDetailScreen
import com.example.androidsp.ui.heroListScreen.HeroListScreen

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.HeroList.route){
        composable(Routes.HeroList.route){
            HeroListScreen(){
                navController.navigate(Routes.HeroDetail.createRouteWithArgs(it))
            }
        }

        composable(
            Routes.HeroDetail.route,
            arguments = listOf(
                navArgument(Routes.HeroDetail.ARG_ID){
                this.type = NavType.IntType
                nullable = false
            })
        ){
            val id = it.arguments?.getInt("id") ?: 0
            println(id)
            HeroDetailScreen(id = id){

            }
        }
    }
}