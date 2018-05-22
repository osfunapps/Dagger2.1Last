package com.karntrehan.posts.osapps.di.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.karntrehan.posts.osapps.di.main.presentation.OsAppsActivityPresenterImpl
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class OsAppsActivity : DaggerAppCompatActivity(), OsAppsActivityView {

    @Inject lateinit var presenter: OsAppsActivityPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.viewReady()
    }

    override fun onPresenterReady() {
    }

}
