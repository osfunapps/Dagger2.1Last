package com.karntrehan.posts.osapps.di.main.presentation


import com.karntrehan.posts.osapps.di.main.view.OsAppsActivityView
import javax.inject.Inject


class OsAppsActivityPresenterImpl @Inject constructor(private var view: OsAppsActivityView){


    fun viewReady() {
    }

}
