package com.karntrehan.posts.core.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.karntrehan.posts.core.BuildConfig
import com.karntrehan.posts.core.di.AppModule
import com.karntrehan.posts.core.di.CoreComponent
import com.karntrehan.posts.core.di.DaggerCoreComponent

lateinit var coreComponent: CoreComponent


fun initCoreDI(app: Application) {
    initStetho(app)
    coreComponent = DaggerCoreComponent.builder().appModule(AppModule(app)).build()
}

private fun initStetho(app: Application) {
    if (BuildConfig.DEBUG)
        Stetho.initializeWithDefaults(app)
}

