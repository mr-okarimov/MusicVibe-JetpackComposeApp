package com.ozodbek.musicvibe

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.getSystemService
import com.ozodbek.musicvibe.utils.NotificationConstants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusicVibeApplication : Application() {
        private val notificationManager by lazy { getSystemService<NotificationManager>() }
        override fun onCreate() {
            super.onCreate()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NotificationConstants.NOTIFICATION_CHANNEL_ID,
                    NotificationConstants.NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = NotificationConstants.NOTIFICATION_CHANNEL_DESC
                    setSound(null, null)
                }

                notificationManager?.createNotificationChannel(channel)
            }

        }
    }