package com.ozodbek.musicvibe

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.ozodbek.musicvibe.di.activityPlayerModule
import com.ozodbek.musicvibe.di.appModule
import com.ozodbek.musicvibe.di.mediaServiceModule
import com.ozodbek.musicvibe.di.viewModelModule
import com.ozodbek.musicvibe.utils.NotificationConstants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class MusicVibeApplication : Application() {

    private val notificationManager by lazy { getSystemService<NotificationManager>() }

    override fun onCreate() {
        super.onCreate()
        val modules = listOf(
            appModule,
            mediaServiceModule,
            activityPlayerModule,
            viewModelModule
        )

        startKoin {
            androidContext(this@MusicVibeApplication)
            koin.loadModules(
                modules,
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        initTimber()
    }

    private fun createNotificationChannel() {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                NotificationConstants.NOTIFICATION_CHANNEL_ID,
                NotificationConstants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = NotificationConstants.NOTIFICATION_CHANNEL_DESC
                setSound(null, null)
            }
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        notificationManager?.createNotificationChannel(channel)
    }

    private fun initTimber() = when {
        BuildConfig.DEBUG -> {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }

        else -> {
            // Timber.plant(CrashlyticsTree())
        }
    }
}
