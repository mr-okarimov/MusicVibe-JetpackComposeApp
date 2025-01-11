package com.ozodbek.musicvibe.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 *@Creator: Ozodbek Karimov
 *@Date: 10/01/2025
 *@File: AudioFilesScreen.java
 */


@Composable
fun PlaySongScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    songUrl: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Play Song Screen", modifier = modifier.clickable {
            onBackClick()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = songUrl)
    }
}