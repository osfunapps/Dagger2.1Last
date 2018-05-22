package com.karntrehan.posts.list.di

import android.arch.persistence.room.Room
import android.content.Context
import android.transition.Scene
import com.karntrehan.posts.commons.data.local.PostDb
import com.karntrehan.posts.commons.data.remote.PostService
import com.karntrehan.posts.core.constants.Constants
import com.karntrehan.posts.core.di.CoreComponent
import com.karntrehan.posts.core.networking.Scheduler
import com.karntrehan.posts.details.di.DetailsComponent
import com.karntrehan.posts.list.ListActivity
import com.karntrehan.posts.list.ListAdapter
import com.karntrehan.posts.list.model.ListDataContract
import com.karntrehan.posts.list.model.ListLocalData
import com.karntrehan.posts.list.model.ListRemoteData
import com.karntrehan.posts.list.model.ListRepository
import com.karntrehan.posts.list.viewmodel.ListViewModelFactory
import com.karntrehan.posts.osapps.di.AppScope
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * where i stopped:
 * 1) go to this link:
 * *https://medium.com/@mydogtom/modularization-part-2-dagger-structure-5c2daf5e849c

 *or this
 * https://proandroiddev.com/dagger-2-part-ii-custom-scopes-component-dependencies-subcomponents-697c1fa1cfc
 *
 * or this
 * https://proandroiddev.com/dagger-2-component-relationships-custom-scopes-8d7e05e70a37
 *
 * and read about component dependency. I guess I Just implemented it wrong.
 * search:

 */
@ListScope
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent {

    //*/Expose to dependent components
    fun postDb(): PostDb

    fun postService(): PostService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(listActivity: ListActivity)


    //fun detailBuilder(detailsComponent: DetailsComponent): DetailsComponent.Builder
}


@Module
@ListScope
class ListModule {

    /*Adapter*/
    @Provides
    @ListScope
    fun adapter(picasso: Picasso): ListAdapter = ListAdapter(picasso)

    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModelFactory(repository: ListDataContract.Repository,compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository,compositeDisposable)

    /*Repository*/
    @Provides
    @ListScope
    fun listRepo(local: ListDataContract.Local, remote: ListDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ListDataContract.Repository = ListRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @ListScope
    fun remoteData(postService: PostService): ListDataContract.Remote = ListRemoteData(postService)

    @Provides
    @ListScope
    fun localData(postDb: PostDb, scheduler: Scheduler): ListDataContract.Local = ListLocalData(postDb, scheduler)

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @ListScope
    fun postDb(context: Context): PostDb = Room.databaseBuilder(context, PostDb::class.java, Constants.Posts.DB_NAME).build()

    @Provides
    @ListScope
    fun postService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}