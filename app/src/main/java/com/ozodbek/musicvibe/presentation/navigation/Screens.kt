package com.ozodbek.musicvibe.presentation.navigation

import kotlinx.serialization.Serializable


/**
 *@Creator: Ozodbek Karimov
 *@Date: 11/01/2025
 *@File: Screens.java
 */


@Serializable
sealed interface Screens {

    @Serializable
    data object AudioFilesScreen : Screens

    @Serializable
    data class PlaySongScreen(val songUrl: String) : Screens

}