package com.ozodbek.musicvibe.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ozodbek.musicvibe.presentation.screens.audiofiles.AudioFilesScreen
import com.ozodbek.musicvibe.presentation.screens.audiofiles.AudioFilesViewModel
import com.ozodbek.musicvibe.presentation.screens.playsong.PlaySongScreen
import com.ozodbek.musicvibe.presentation.screens.SharedSongPlayerViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


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

    val sharedViewModel = hiltViewModel<SharedSongPlayerViewModel>()



    val currentSelectedSong by sharedViewModel.currentSelectedSong.collectAsStateWithLifecycle()
    NavHost(
        navController = navHostController,
        startDestination = Screens.AudioFilesScreen,
        modifier = modifier
    ) {
        composable<Screens.AudioFilesScreen> {
            val viewModel = hiltViewModel<AudioFilesViewModel>()

            val files by viewModel.audioFiles.collectAsStateWithLifecycle()
            val sortState by viewModel.sortState.collectAsStateWithLifecycle()

            AudioFilesScreen(
                onNavigateToPlaySong = { songUri ->
                    val encodedUri = Uri.encode(songUri)
                    navHostController.navigate(Screens.PlaySongScreen(encodedUri))
                },
                currentSong = currentSelectedSong,
                music = files,
                sortState = sortState,
                onSortEvents = viewModel::onSortEvents,
                onItemSelect = sharedViewModel::onSongSelect,
                onSongEvents = sharedViewModel::onPlaySongEvents,
            )
        }
        composable<Screens.PlaySongScreen> { backStackEntry ->
            val toRoute: Screens.PlaySongScreen = backStackEntry.toRoute()
            val trackData by sharedViewModel.trackDataFlow.collectAsStateWithLifecycle()
            PlaySongScreen(
                onBackClick = navHostController::navigateUp,
                songState = currentSelectedSong,
                onSongEvents = sharedViewModel::onPlaySongEvents,
                trackData = trackData
            )
        }
    }
}
