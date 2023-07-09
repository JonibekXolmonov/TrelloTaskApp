package uz.realsoft.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.realsoft.task.data.repository.AuthRepositoryImpl
import uz.realsoft.task.data.repository.MainRepositoryImpl
import uz.realsoft.task.domain.datasource.local.LocalDataSource
import uz.realsoft.task.domain.datasource.remote.RemoteDataSource
import uz.realsoft.task.domain.datasource.storage.SecureStorage
import uz.realsoft.task.domain.repository.AuthRepository
import uz.realsoft.task.domain.repository.MainRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MainRepository = MainRepositoryImpl(localDataSource, remoteDataSource)


    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: RemoteDataSource,
        secureStorage: SecureStorage
    ): AuthRepository = AuthRepositoryImpl(remoteDataSource,secureStorage)

}