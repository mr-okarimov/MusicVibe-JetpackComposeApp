package com.ozodbek.musicvibe.domain.music

import com.ozodbek.musicvibe.domain.models.MusicResourceModel

interface MusicFileReader {

    suspend fun readMusicFiles(isAudioBooksToo: Boolean = false): List<MusicResourceModel>
}