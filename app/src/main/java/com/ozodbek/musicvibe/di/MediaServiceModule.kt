package com.ozodbek.musicvibe.di

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.ForwardingPlayer
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaConstants
import androidx.media3.session.MediaSession
import com.ozodbek.musicvibe.data.player.MediaSessionCallbacks
import com.ozodbek.musicvibe.utils.AppConstants
import com.ozodbek.musicvibe.utils.MediaSessionConstants
import org.koin.dsl.module

@SuppressLint("UnsafeOptInUsageError")
val mediaServiceModule = module {

    // Provide AudioAttributes
    single {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    // Provide ExoPlayer
    single { (context: Context, audioAttributes: AudioAttributes) ->
        ExoPlayer.Builder(context)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }

    single { (player: ExoPlayer) ->
        object : ForwardingPlayer(player) {
            override fun getAvailableCommands(): Player.Commands {
                return super.getAvailableCommands()
                    .buildUpon()
                    .removeAll(
                        COMMAND_SEEK_TO_PREVIOUS,
                        COMMAND_SEEK_TO_NEXT,
                    )
                    .build()
            }
        }
    }

    // Provide MediaSession
    single { (context: Context, player: ForwardingPlayer) ->
        val pendingIntent = context.packageManager
            .getLaunchIntentForPackage(context.packageName)
            ?.apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            ?.let { intent ->
                PendingIntent.getActivity(
                    context,
                    AppConstants.ACTIVITY_PENDING_REQUEST_ID,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
                )
            }

        val extras = Bundle().apply {
            putBoolean(MediaConstants.EXTRAS_KEY_SLOT_RESERVATION_SEEK_TO_PREV, true)
            putBoolean(MediaConstants.EXTRAS_KEY_SLOT_RESERVATION_SEEK_TO_NEXT, true)
        }

        MediaSession.Builder(context, player)
            .apply {
                pendingIntent?.let { setSessionActivity(it) }
            }
            .setExtras(extras)
            .setCallback(MediaSessionCallbacks())
            .setId(MediaSessionConstants.MEDIA_SESSION_TAG)
            .build()
    }
}
