package com.ozodbek.musicvibe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import com.ozodbek.musicvibe.presentation.composables.NoReadPermissions
import com.ozodbek.musicvibe.presentation.navigation.NavigationGraph
import com.ozodbek.musicvibe.presentation.util.checkAudioReadPermissions
import com.ozodbek.musicvibe.ui.theme.MusicVibeTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val listenableFuture: ListenableFuture<MediaController> by inject()

    override fun onDestroy() {
        MediaController.releaseFuture(listenableFuture)
        super.onDestroy()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicVibeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isReadPermissionProvided = checkAudioReadPermissions()

                    when {
                        isReadPermissionProvided -> NavigationGraph()

                        else -> NoReadPermissions()
                    }
                }
            }
        }
    }
}
