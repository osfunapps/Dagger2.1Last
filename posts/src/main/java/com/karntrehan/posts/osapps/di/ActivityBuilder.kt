package com.karntrehan.posts.osapps.di

import com.karntrehan.posts.osapps.di.AppScope
import com.karntrehan.posts.osapps.di.main.di.OsAppsActivityModule
import com.karntrehan.posts.osapps.di.main.view.OsAppsActivity
import com.karntrehan.posts.osapps.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
package com.karntrehan.posts.osapps.di

import com.karntrehan.posts.osapps.di.scopes.PerActivity
import com.karntrehan.posts.osapps.di.main.di.OsAppsActivityModule
import com.karntrehan.posts.osapps.di.main.view.OsAppsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

*/
/**
 * Created by osapps on 03/05/2018.
 */

/*
 * A module meant to:
 * 1) connect the application module with it's corresponding activities
 * 2) state which modules each app should use
 *
 * Every activity should be declared here, with at least one module.
*/

@PerActivity
@Module
abstract class ActivityBuilder {


    @PerActivity
    @ContributesAndroidInjector(modules = [
        OsAppsActivityModule::class
    ])
    internal abstract fun bindOsAppsActivity(): OsAppsActivity

}


