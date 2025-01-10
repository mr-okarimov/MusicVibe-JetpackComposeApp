package com.ozodbek.musicvibe

import android.app.Application
import com.ozodbek.musicvibe.di.activityPlayerModule
import com.ozodbek.musicvibe.di.appModule
import com.ozodbek.musicvibe.di.mediaServiceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber


class MusicVibeApplication : Application() {

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