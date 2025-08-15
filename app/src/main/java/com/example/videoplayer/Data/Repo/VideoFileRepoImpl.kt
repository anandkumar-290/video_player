package com.example.videoplayer.Data.Repo

import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import com.example.videoplayer.Data.model.VideoFile
import com.example.videoplayer.Domain.repo.VideoFileRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File
import java.util.ArrayList

class VideoFileRepoImpl : VideoFileRepo {
    override suspend fun getAllVideos(application: Application): Flow<ArrayList<VideoFile>> {
        val allVideo = ArrayList<VideoFile>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DISPLAY_NAME
        )

        val Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val memoryCursor = application.contentResolver.query(Uri, projection, null, null, null)

        if (memoryCursor != null){
            while (memoryCursor.moveToNext()){
                val id = memoryCursor.getString(0)
                val path = memoryCursor.getString(1)
                val title = memoryCursor.getString(2)
                val size = memoryCursor.getString(3)
                val dateAdded = memoryCursor.getString(4)
                val duration = memoryCursor.getString(5)
                val fileName = memoryCursor.getString(6)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toLong()
                )

                val videoFile = VideoFile(
                    id = id,
                    path = path,
                    title = title,
                    fileName = fileName,
                    size = size,
                    duration = duration,
                    dateAdded = dateAdded,
                    thumbnailUrl = contentUri.toString()
                )
                allVideo.add(videoFile)
                if (memoryCursor.isLast){
                    break
                }
            }
            memoryCursor.close()
        }

        return flow {
            emit(allVideo)
        }
    }

    override suspend fun getAllFolders(application: Application): Flow<Map<String, List<VideoFile>>> {

        val allVideo = getAllVideos(application).first()
        val videoByFolder = allVideo.groupBy { File(it.path).parent?: "Unknown Folder" }

        return flow {
            emit(videoByFolder)
        }

    }
}