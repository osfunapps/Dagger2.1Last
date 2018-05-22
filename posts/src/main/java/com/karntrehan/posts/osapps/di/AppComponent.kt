package com.karntrehan.posts.osapps.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.karntrehan.posts.commons.data.local.PostDb
import com.karntrehan.posts.commons.data.remote.PostService
import com.karntrehan.posts.core.constants.Constants
import com.karntrehan.posts.core.di.CoreComponent
import com.karntrehan.posts.core.networking.Scheduler
import com.karntrehan.posts.details.di.DetailsComponent
import com.karntrehan.posts.list.ListActivity
import com.karntrehan.posts.list.ListAdapter
import com.karntrehan.posts.list.di.ListComponent
import com.karntrehan.posts.list.di.ListModule
import com.karntrehan.posts.list.model.ListDataContract
import com.karntrehan.posts.list.model.ListLocalData
import com.karntrehan.posts.list.model.ListRemoteData
import com.karntrehan.posts.list.model.ListRepository
import com.karntrehan.posts.list.viewmodel.ListViewModelFactory
import com.squareup.picasso.Picasso
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

//todo: to move AndroidSupportInjectionModule::class to core!
@AppScope
@Component(dependencies = [CoreComponent::class],
        modules = [AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface AppComponent: AndroidInjector<DaggerApplication> {

    //a generic builder
    override fun inject(instance: DaggerApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }


}