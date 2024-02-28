package com.example.architecturepractice_todoapp.di

import com.example.architecturepractice_todoapp.data.repository.DefaultTaskRepository
import com.example.architecturepractice_todoapp.data.source.local.LocalDataSource
import com.example.architecturepractice_todoapp.data.source.local.room.RoomDataSource
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLocalDataSource(
        roomDataSource: RoomDataSource
    ): LocalDataSource


    @Binds
    @ViewModelScoped
    abstract fun bindTaskRepository(
        defaultTaskRepository: DefaultTaskRepository
    ): TaskRepository

}
