package com.example.androidsp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidsp.ui.heroDetailScreen.HeroDetailScreen
import com.example.androidsp.ui.heroDetailScreen.HeroLikeListScreen
import com.example.androidsp.ui.heroListScreen.HeroListScreen
import okhttp3.Route

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.HeroListRoute.route){
        composable(Routes.HeroListRoute.route){
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
            HeroDetailScreen(id = id,
                navigateToComics = {
                    navController.navigate(Routes.Comics.createRouteWithArgs(id))
                },
                navigateToDetail = {
                    navController.navigate(Routes.HeroDetail.createRouteWithArgs(id))
                },
                navigateToSeries = {
                    navController.navigate(Routes.Series.createRouteWithArgs(id))
                })
        }

        composable(
            Routes.Comics.route,
            arguments = listOf(
                navArgument(Routes.Comics.ARG_ID){
                    this.type = NavType.IntType
                    nullable = false
                })
        ){
            val id = it.arguments?.getInt("id") ?: 0
            println(id)
            HeroLikeListScreen(id = id, isSerie = false,
                navigateToComics = {
                    navController.navigate(Routes.Comics.createRouteWithArgs(id))
                },
                navigateToDetail = {
                    navController.navigate(Routes.HeroDetail.createRouteWithArgs(id))
                },
                navigateToSeries = {
                    navController.navigate(Routes.Series.createRouteWithArgs(id))
                })
        }

        composable(
            Routes.Series.route,
            arguments = listOf(
                navArgument(Routes.Series.ARG_ID){
                    this.type = NavType.IntType
                    nullable = false
                })
        ){
            val id = it.arguments?.getInt("id") ?: 0
            println(id)
            HeroLikeListScreen(id = id, isSerie = true,
                navigateToComics = {
                navController.navigate(Routes.Comics.createRouteWithArgs(id))
            },
                navigateToDetail = {
                    navController.navigate(Routes.HeroDetail.createRouteWithArgs(id))
                },
                navigateToSeries = {
                    navController.navigate(Routes.Series.createRouteWithArgs(id))
                })
        }
    }
}