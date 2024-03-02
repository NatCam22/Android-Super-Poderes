package com.example.androidsp.ui.navigation

import com.example.androidsp.ui.navigation.Routes.HeroDetail.ARG_ID

sealed class Routes(val route: String){
    object HeroList : Routes("heros")
    object HeroDetail: Routes(HERODETAIL_ROUTE_TEMPLATE){
        const val ARG_ID = "id"
        fun createRouteWithArgs(id: Int): String {
            return "hero/$id"
        }
    }

    companion object {
        const val HERODETAIL_ROUTE_TEMPLATE = "hero/{$ARG_ID}"
    }
}