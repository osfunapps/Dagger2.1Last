package com.karntrehan.posts

import android.app.Application
import com.karntrehan.posts.core.application.coreComponent
import com.karntrehan.posts.core.application.initCoreDI
import com.karntrehan.posts.osapps.di.AppComponent
import com.karntrehan.posts.osapps.di.DaggerAppComponent

/**
 * Created by osapps on 21/05/2018.
 */

class App : Application() {

    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        initCoreDI(this)
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).build()
    }
}
