package com.ozodbek.musicvibe.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 *@Creator: Ozodbek Karimov
 *@Date: 10/01/2025
 *@File: AudioFilesScreen.java
 */


@Composable
fun AudioFilesScreen(
    modifier: Modifier = Modifier, onNavigateToPlaySong: (String) -> Unit
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Audio Files Screen",
            modifier = modifier.clickable {
                onNavigateToPlaySong("songUrl")
            }
        )
    }
}