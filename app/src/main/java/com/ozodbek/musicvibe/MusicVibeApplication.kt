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
import com.ozodbek.musicvibe.utils.NotificationConstants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber


class MusicVibeApplication : Application() {

    private val notificationManager by lazy { getSystemService<NotificationManager>() }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(
            appModule,
            mediaServiceModule,
            activityPlayerModule,
        )


        startKoin {
            androidContext(this@MusicVibeApplication)
            koin.loadModules(
                modules,
            )
        }

        val channel = NotificationChannel(
            NotificationConstants.NOTIFICATION_CHANNEL_ID,
            NotificationConstants.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = NotificationConstants.NOTIFICATION_CHANNEL_DESC
            setSound(null, null)
        }

        notificationManager?.createNotificationChannel(channel)

        initTimber()
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
          //  Timber.plant(CrashlyticsTree())
        }
    }
}