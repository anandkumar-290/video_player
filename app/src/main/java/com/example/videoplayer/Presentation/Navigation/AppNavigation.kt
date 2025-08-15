package com.example.videoplayer.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.videoplayer.Presentation.App.App
import com.example.videoplayer.Presentation.FolderVideosScreen.FolderVideosScreen
import com.example.videoplayer.Presentation.HomeScreen.HomeScreen
import com.example.videoplayer.Presentation.VideoPlayerScreen.VideoPlayerScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItems.App) {

        composable<NavigationItems.App> {
            App(navController = navController)
        }

        composable<NavigationItems.HomeScreen>{
            HomeScreen(navController)
        }

        composable<NavigationItems.PlayerScreen> {BackStackEntry ->
            val url : NavigationItems.PlayerScreen = BackStackEntry.toRoute()
            VideoPlayerScreen(url.videoUrl, navController = navController)
        }

        composable<NavigationItems.FolderVideosScreen> {
            val folderName : NavigationItems.FolderVideosScreen = it.toRoute()
            FolderVideosScreen(navController = navController,folderName = folderName.folderName)

        }
    }

}