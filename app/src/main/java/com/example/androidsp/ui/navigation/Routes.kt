package com.example.androidsp.ui.navigation

import com.example.androidsp.ui.navigation.Routes.HeroDetail.ARG_ID

sealed class Routes(val route: String){
    object HeroListRoute : Routes("heros")
    object HeroDetail: Routes(HERODETAIL_ROUTE_TEMPLATE){
        const val ARG_ID = "id"
        fun createRouteWithArgs(id: Int): String {
            return "hero/$id"
        }
    }
    object Series: Routes(SERIES_ROUTE_TEMPLATE){
        const val ARG_ID = "id"
        fun createRouteWithArgs(id: Int): String {
            return "hero/series/$id"
        }
    }
    object Comics: Routes(COMICS_ROUTE_TEMPLATE){
        const val ARG_ID = "id"
        fun createRouteWithArgs(id: Int): String {
            return "hero/comics/$id"
        }
    }

    companion object {
        const val HERODETAIL_ROUTE_TEMPLATE = "hero/{$ARG_ID}"
        const val SERIES_ROUTE_TEMPLATE = "hero/series/{$ARG_ID}"
        const val COMICS_ROUTE_TEMPLATE = "hero/comics/{$ARG_ID}"
    }
}