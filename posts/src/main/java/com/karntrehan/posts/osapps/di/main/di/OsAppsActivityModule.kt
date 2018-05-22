package com.karntrehan.posts.osapps.di.main.di

import com.karntrehan.posts.osapps.di.AppScope
import com.karntrehan.posts.osapps.di.main.presentation.OsAppsActivityPresenterImpl
import com.karntrehan.posts.osapps.di.main.view.OsAppsActivity
import com.karntrehan.posts.osapps.di.main.view.OsAppsActivityView
import com.karntrehan.posts.osapps.di.scopes.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by osapps on 03/05/2018.
 */

@Module
class OsAppsActivityModule {

    @Provides
    fun provideMainView(osAppsActivity: OsAppsActivity) : OsAppsActivityView = osAppsActivity

    @Provides
    @PerActivity
    fun provideMainPresenter(OsAppsActivityView: OsAppsActivity): OsAppsActivityPresenterImpl {
        return OsAppsActivityPresenterImpl(OsAppsActivityView)
    }

}

