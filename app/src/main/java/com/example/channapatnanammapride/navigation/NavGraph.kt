package com.example.channapatnanammapride.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.channapatnanammapride.presentation.catalog.ToyCatalogScreen
import com.example.channapatnanammapride.presentation.catalog.ToyDetailsScreen
import com.example.channapatnanammapride.presentation.home.HomeScreen
import com.example.channapatnanammapride.presentation.howitsmade.HowItsMadeScreen
import com.example.channapatnanammapride.presentation.meetthemaker.ArtisanProfileScreen
import com.example.channapatnanammapride.presentation.meetthemaker.MeetTheMakerScreen
import com.example.channapatnanammapride.presentation.splash.SplashScreen
import com.example.channapatnanammapride.presentation.verify.VerifyToyScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToHome = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToVerify = { navController.navigate(Screen.VerifyToy.route) },
                onNavigateToCatalog = { navController.navigate(Screen.ToyCatalog.route) },
                onNavigateToHowItsMade = { navController.navigate(Screen.HowItsMade.route) },
                onNavigateToMeetMaker = { navController.navigate(Screen.MeetTheMaker.route) },
                onNavigateToSettings = { /* navController.navigate(Screen.Settings.route) */ }
            )
        }
        composable(Screen.VerifyToy.route) {
            VerifyToyScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.ToyCatalog.route) {
            ToyCatalogScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToToy = { toyId -> navController.navigate(Screen.ToyDetails.createRoute(toyId)) }
            )
        }
        composable(Screen.HowItsMade.route) {
            HowItsMadeScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.MeetTheMaker.route) {
            MeetTheMakerScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToArtisan = { artisanId -> navController.navigate(Screen.ArtisanProfile.createRoute(artisanId)) }
            )
        }
        composable(Screen.ToyDetails.route) { backStackEntry ->
            val toyId = backStackEntry.arguments?.getString("toyId") ?: ""
            ToyDetailsScreen(toyId = toyId, onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.ArtisanProfile.route) { backStackEntry ->
            val artisanId = backStackEntry.arguments?.getString("artisanId") ?: ""
            ArtisanProfileScreen(artisanId = artisanId, onNavigateBack = { navController.popBackStack() })
        }
    }
}

