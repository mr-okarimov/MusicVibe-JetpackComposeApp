package com.ozodbek.musicvibe.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.ozodbek.musicvibe.data.player.MediaControllerListener
import com.ozodbek.musicvibe.data.player.MediaPlayBackService
import org.koin.core.module.Module
import org.koin.dsl.module

val activityPlayerModule: Module = module {

    // Provide SessionToken
    factory { (context: Context) ->
        SessionToken(
            context,
            ComponentName(context, MediaPlayBackService::class.java)
        )
    }

    // Provide ListenableFuture<MediaController>
    factory { (context: Context, session: SessionToken) ->
        MediaController.Builder(context, session).buildAsync()
    }

    // Provide MediaControllerListener
    factory { (future: ListenableFuture<MediaController>) ->
        MediaControllerListener(future)
    }
}