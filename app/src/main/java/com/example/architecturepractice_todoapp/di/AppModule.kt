package com.example.architecturepractice_todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.architecturepractice_todoapp.data.source.local.room.TaskDao
import com.example.architecturepractice_todoapp.data.source.local.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideTaskDao(
        taskDB: TaskDatabase
    ): TaskDao {
        return taskDB.dao
    }

}
