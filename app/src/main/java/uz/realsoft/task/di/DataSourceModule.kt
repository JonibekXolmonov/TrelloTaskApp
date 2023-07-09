package uz.realsoft.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.realsoft.task.common.SharedPref
import uz.realsoft.task.data.db.TaskDao
import uz.realsoft.task.data.network.ApiService
import uz.realsoft.task.data.source.cache.impl.CacheDataSourceImpl
import uz.realsoft.task.data.source.local.impl.LocalDataSourceImpl
import uz.realsoft.task.data.source.remote.impl.RemoteDataSourceImpl
import uz.realsoft.task.data.source.storage.impl.SecureStorageImpl
import uz.realsoft.task.domain.datasource.cache.CacheDataSource
import uz.realsoft.task.domain.datasource.local.LocalDataSource
import uz.realsoft.task.domain.datasource.remote.RemoteDataSource
import uz.realsoft.task.domain.datasource.storage.SecureStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        taskDao: TaskDao
    ): LocalDataSource = LocalDataSourceImpl(taskDao = taskDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource = RemoteDataSourceImpl(apiService = apiService)

    @Provides
    @Singleton
    fun provideCacheDataSource(
        taskDao: TaskDao
    ): CacheDataSource = CacheDataSourceImpl(taskDao = taskDao)

    @Provides
    @Singleton
    fun provideSecureStorage(
        sharedPref: SharedPref
    ): SecureStorage = SecureStorageImpl(sharedPref)

}