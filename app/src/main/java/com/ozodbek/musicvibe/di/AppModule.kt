package com.ozodbek.musicvibe.di

import android.content.Context
import com.ozodbek.musicvibe.data.music.MusicFileReaderImpl
import com.ozodbek.musicvibe.domain.music.MusicFileReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun musicFileReader(
        @ApplicationContext context: Context
    ): MusicFileReader {
        return MusicFileReaderImpl(context)
    }
}