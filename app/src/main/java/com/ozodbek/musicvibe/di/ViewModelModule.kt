package com.ozodbek.musicvibe.di

import com.ozodbek.musicvibe.presentation.screens.AudioFilesViewModel
import com.ozodbek.musicvibe.presentation.screens.SharedSongPlayerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


/**
 *@Creator: Ozodbek Karimov
 *@Date: 11/01/2025
 *@File: ViewModelModule.java
 */



val viewModelModule = module {
    viewModelOf(::AudioFilesViewModel)
    viewModelOf(::SharedSongPlayerViewModel)
}