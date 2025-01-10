package com.ozodbek.musicvibe

import android.app.Application
import com.ozodbek.musicvibe.di.activityPlayerModule
import com.ozodbek.musicvibe.di.appModule
import com.ozodbek.musicvibe.di.mediaServiceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


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
    }
}