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

    factory { (context: Context) ->
        SessionToken(
            context,
            ComponentName(context, MediaPlayBackService::class.java)
        )
    }

    factory { (context: Context, session: SessionToken) ->
        MediaController.Builder(context, session).buildAsync()
    }

    factory { (future: ListenableFuture<MediaController>) ->
        MediaControllerListener(future)
    }
}