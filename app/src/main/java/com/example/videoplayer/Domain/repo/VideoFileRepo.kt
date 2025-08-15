package com.example.videoplayer.Domain.repo

import android.app.Application
import com.example.videoplayer.Data.model.VideoFile
import kotlinx.coroutines.flow.Flow
import java.util.ArrayList

interface VideoFileRepo {
    suspend fun getAllVideos(application : Application): Flow<ArrayList<VideoFile>>
    suspend fun getAllFolders(application : Application): Flow<Map<String, List<VideoFile>>>

}