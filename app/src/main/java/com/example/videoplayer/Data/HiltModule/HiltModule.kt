package com.example.videoplayer.Data.HiltModule

import com.example.videoplayer.Data.Repo.VideoFileRepoImpl
import com.example.videoplayer.Domain.repo.VideoFileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun ProvidVideoFileRepo() : VideoFileRepo {
        return VideoFileRepoImpl()

    }
}