package com.example.channapatnanammapride.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object VerifyToy : Screen("verify_toy")
    object ToyDetails : Screen("toy_details/{toyId}") {
        fun createRoute(toyId: String) = "toy_details/$toyId"
    }
    object HowItsMade : Screen("how_its_made")
    object MeetTheMaker : Screen("meet_the_maker")
    object ArtisanProfile : Screen("artisan_profile/{artisanId}") {
        fun createRoute(artisanId: String) = "artisan_profile/$artisanId"
    }
    object ToyCatalog : Screen("toy_catalog")
    object Settings : Screen("settings")
}
