package com.ozodbek.musicvibe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ozodbek.musicvibe.presentation.screens.AudioFilesScreen
import com.ozodbek.musicvibe.presentation.screens.PlaySongScreen


/**
 *@Creator: Ozodbek Karimov
 *@Date: 11/01/2025
 *@File: NavigationGraph.java
 */


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()


    NavHost(
        navController = navHostController,
        startDestination = Screens.AudioFilesScreen,
        modifier = modifier
    ) {
        composable<Screens.AudioFilesScreen> {
            AudioFilesScreen(
                onNavigateToPlaySong = { songUrl ->
                    navHostController.navigate(Screens.PlaySongScreen(songUrl))
                }
            )
        }
        composable<Screens.PlaySongScreen> { backStackEntry ->
            val toRoute: Screens.PlaySongScreen = backStackEntry.toRoute()
            PlaySongScreen(
                onBackClick = navHostController::navigateUp,
                songUrl = toRoute.songUrl
            )
        }
    }
}
