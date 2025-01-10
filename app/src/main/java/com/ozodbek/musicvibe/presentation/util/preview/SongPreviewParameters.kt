package com.ozodbek.musicvibe.presentation.util.preview

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.ozodbek.musicvibe.presentation.util.states.CurrentSelectedSongState
import com.ozodbek.musicvibe.domain.models.MusicResourceModel

class SongPreviewParameters : CollectionPreviewParameterProvider<MusicResourceModel>(
    listOf(
        FakeMusicModels.fakeMusicResourceModel,
        FakeMusicModels.fakeMusicResourceModelExtraLongNames
    )
)

class CurrentSongPreviewParams : CollectionPreviewParameterProvider<CurrentSelectedSongState>(
    listOf(
        CurrentSelectedSongState(
            showBottomBar = true,
            current = FakeMusicModels.fakeMusicResourceModel
        ),
        CurrentSelectedSongState(
            showBottomBar = true,
            current = FakeMusicModels.fakeMusicResourceModelExtraLongNames,
            isRepeating = true
        ),
        CurrentSelectedSongState(
            showBottomBar = true,
            current = FakeMusicModels.fakeMusicResourceModel,
            isPlaying = true
        ),
        CurrentSelectedSongState(
            showBottomBar = true,
            current = FakeMusicModels.fakeMusicResourceModelExtraLongNames,
            isRepeating = true,
            isPlaying = true
        )
    )
)