package com.ozodbek.musicvibe.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ozodbek.musicvibe.R
import com.ozodbek.musicvibe.domain.models.MusicResourceModel
import com.ozodbek.musicvibe.presentation.composables.MusicCardBasic
import com.ozodbek.musicvibe.presentation.composables.MusicPlayerBar
import com.ozodbek.musicvibe.presentation.composables.MusicSortOptions
import com.ozodbek.musicvibe.presentation.util.states.ChangeSortOrderEvents
import com.ozodbek.musicvibe.presentation.util.states.CurrentSelectedSongState
import com.ozodbek.musicvibe.presentation.util.states.MusicSortState
import com.ozodbek.musicvibe.presentation.util.states.SongEvents


/**
 *@Creator: Ozodbek Karimov
 *@Date: 10/01/2025
 *@File: AudioFilesScreen.java
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioFilesScreen(
    modifier: Modifier = Modifier,
    onNavigateToPlaySong: (String) -> Unit,
    sortState: MusicSortState,
    onSortEvents: (ChangeSortOrderEvents) -> Unit,
    currentSong: CurrentSelectedSongState,
    music: List<MusicResourceModel>,
    onItemSelect: (MusicResourceModel) -> Unit,
    onSongEvents: (SongEvents) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.audio_route_title),
                style = MaterialTheme.typography.headlineSmall
            )
        }, actions = {
            IconButton(onClick = { onSortEvents(ChangeSortOrderEvents.ToggleDialogState) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort_order),
                    contentDescription = "Sort Order",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        )
    }, bottomBar = {
        MusicPlayerBar(currentSong = currentSong,
            onSongEvents = onSongEvents,
            modifier = Modifier.clickable {
                currentSong.current?.uri?.let { uri -> onNavigateToPlaySong(uri) }
            })
    }) { paddingValues ->

        MusicSortOptions(sortState = sortState, onSortOrderChange = { order ->
            onSortEvents(ChangeSortOrderEvents.OnOrderChanged(order))
        }, onDismissRequest = {
            onSortEvents(ChangeSortOrderEvents.ToggleDialogState)
        })
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 10.dp,
                bottom = paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 10.dp),
        ) {
            itemsIndexed(music, key = { _, item -> item.id }) { _, item ->
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onItemSelect(item) }
                MusicCardBasic(
                    music = item,
                    modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
                )
            }
        }
    }
}