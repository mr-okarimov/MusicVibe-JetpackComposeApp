package com.ozodbek.musicvibe.di

import android.content.Context
import com.ozodbek.musicvibe.data.music.MusicFileReaderImpl
import com.ozodbek.musicvibe.domain.music.MusicFileReader
import org.koin.dsl.module

val appModule = module {

    single<MusicFileReader> { (context: Context) ->
        MusicFileReaderImpl(context)
    }
}
