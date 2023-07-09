package uz.realsoft.task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.realsoft.task.domain.use_case.LoginUseCase
import uz.realsoft.task.domain.use_case.TaskUseCase
import uz.realsoft.task.domain.use_case.impl.LoginUseCaseImpl
import uz.realsoft.task.domain.use_case.impl.TaskUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase


    @Binds
    fun bindTaskUseCase(useCase: TaskUseCaseImpl): TaskUseCase

}