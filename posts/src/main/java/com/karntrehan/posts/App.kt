package com.karntrehan.posts

import android.app.Application
import com.karntrehan.posts.core.application.coreComponent
import com.karntrehan.posts.core.application.initCoreDI
import com.karntrehan.posts.osapps.di.AppComponent
import com.karntrehan.posts.osapps.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by osapps on 21/05/2018.
 */

class App : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        initCoreDI(this)
        appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    /*override fun onCreate() {
        super.onCreate()
        initCoreDI(this)
        initAppComponent()
    }



    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).build()
    }*/
}
