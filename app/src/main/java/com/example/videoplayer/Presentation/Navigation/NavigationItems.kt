package com.example.videoplayer.Presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationItems {
    @Serializable
    object App

    @Serializable
    object HomeScreen

    @Serializable
    data class PlayerScreen(val videoUrl: String?, val title: String? = null)

    @Serializable
    data class FolderVideosScreen(val folderName: String)

    @Serializable
    data class AllVideosFolder(val folderName: String)

}