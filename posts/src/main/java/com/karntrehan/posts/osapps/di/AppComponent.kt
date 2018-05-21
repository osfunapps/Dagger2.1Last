package com.karntrehan.posts.osapps.di

import android.arch.persistence.room.Room
import android.content.Context
import com.karntrehan.posts.commons.data.local.PostDb
import com.karntrehan.posts.commons.data.remote.PostService
import com.karntrehan.posts.core.constants.Constants
import com.karntrehan.posts.core.di.CoreComponent
import com.karntrehan.posts.core.networking.Scheduler
import com.karntrehan.posts.list.ListActivity
import com.karntrehan.posts.list.ListAdapter
import com.karntrehan.posts.list.di.ListModule
import com.karntrehan.posts.list.model.ListDataContract
import com.karntrehan.posts.list.model.ListLocalData
import com.karntrehan.posts.list.model.ListRemoteData
import com.karntrehan.posts.list.model.ListRepository
import com.karntrehan.posts.list.viewmodel.ListViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@AppScope
@Component(dependencies = [CoreComponent::class], modules = [HappyModule::class])
interface AppComponent {

    //Expose to dependent components
    fun postDb(): PostDb

    fun postService(): PostService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(listActivity: ListActivity)
}

@Module
@AppScope
class HappyModule {

    /*Adapter*/
    @Provides
    @AppScope
    fun adapter(picasso: Picasso): ListAdapter = ListAdapter(picasso)

    /*ViewModel*/
    @Provides
    @AppScope
    fun listViewModelFactory(repository: ListDataContract.Repository,compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository,compositeDisposable)

    /*Repository*/
    @Provides
    @AppScope
    fun listRepo(local: ListDataContract.Local, remote: ListDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ListDataContract.Repository = ListRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @AppScope
    fun remoteData(postService: PostService): ListDataContract.Remote = ListRemoteData(postService)

    @Provides
    @AppScope
    fun localData(postDb: PostDb, scheduler: Scheduler): ListDataContract.Local = ListLocalData(postDb, scheduler)

    @Provides
    @AppScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @AppScope
    fun postDb(context: Context): PostDb = Room.databaseBuilder(context, PostDb::class.java, Constants.Posts.DB_NAME).build()

    @Provides
    @AppScope
    fun postService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}