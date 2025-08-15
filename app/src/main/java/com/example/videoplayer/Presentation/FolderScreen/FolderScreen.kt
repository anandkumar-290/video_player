package com.example.videoplayer.Presentation.FolderScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.Presentation.Utils.folderCart
import com.example.videoplayer.Presentation.viewModel.ViewModel

@Composable
fun FolderScreen(

    navController: NavHostController,
    viewModel: ViewModel = hiltViewModel()

    ) {

    val videosFolder =  viewModel.folderList.collectAsState().value

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)

    ){
        items(videosFolder.toList()) { (folderName, videos) ->

            folderCart(
                folderName = folderName,
                videoCount = videos.size,
                navController = navController
            )
        }
    }
}