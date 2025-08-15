package com.example.videoplayer.Presentation.FolderVideosScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.Data.model.VideoFile
import com.example.videoplayer.Presentation.Utils.CustomTopAppBar
import com.example.videoplayer.Presentation.Utils.VideoCard
import com.example.videoplayer.Presentation.viewModel.ViewModel



@Composable
fun FolderVideosScreen(
    navController: NavHostController,
    folderName: String,
    viewModel: ViewModel = hiltViewModel()
) {

    Scaffold (
        topBar = { CustomTopAppBar(topAppbarText = folderName, navController = navController)}
    ){ innerPadding ->

        val videosFolder = viewModel.folderList.collectAsState().value
        val videosInFolder = videosFolder[folderName] ?: emptyList()

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(videosInFolder){video ->

                VideoCard(
                    path = video.path,
                    title = video.title ?: "Untitled",
                    size = video.size ?: "Unknown",
                    duration = video.duration ?: "Unknown",
                    dateAdded = video.dateAdded ?: "Unknown",
                    fileNames = video.fileName ?: "Unknown",
                    thumbnail = video.thumbnailUrl ?: "Unknown",
                    id = video.id ?: "Unknown",
                    navController = navController
                )

            }
        }
    }
}