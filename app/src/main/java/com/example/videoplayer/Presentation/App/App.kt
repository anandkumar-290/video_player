//package com.example.videoplayer.Presentation.App
//
//import android.Manifest
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.example.videoplayer.Presentation.viewModel.ViewModel
//// Replaced dagger.hilt.android.lifecycle.HiltViewModel with the correct import below
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.videoplayer.Presentation.HomeScreen.HomeScreen
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.isGranted
//import com.google.accompanist.permissions.rememberPermissionState
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun App(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    viewModel: ViewModel = hiltViewModel()
//
//) {
//    val mediaPermission = rememberPermissionState(permission = Manifest.permission.READ_MEDIA_VIDEO)
//    val mediaPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
//
//        if(it){
//            viewModel.showUi.value = true
//        }else{
//            viewModel.showUi.value = false
//
//        }
//
//        LaunchedEffect(key1 = mediaPermission) {
//
//            if(!mediaPermission.status.isGranted){
//
//                mediaPermissionLauncher.launch(
//                    Manifest.permission.READ_MEDIA_VIDEO
//
//                )
//            }else{
//                viewModel.showUi.value = true
//
//            }
//        }
//        val state = viewModel.showUi.collectAsState()
//        if(state.value){
//
//            HomeScreen(navController = navController)
//
//
//        }
//        else{
//
//            Box (
//                modifier = modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center,
//            ){
//                Text( text = "please grant permission")
//            }
//        }
//    }
//
//}

package com.example.videoplayer.Presentation.App

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.Presentation.HomeScreen.HomeScreen
import com.example.videoplayer.Presentation.viewModel.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale // Import for shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun App(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel()
) {
    val mediaPermissionState = rememberPermissionState(permission = Manifest.permission.READ_MEDIA_VIDEO)

    // This LaunchedEffect runs on initial composition and whenever mediaPermissionState.status changes.
    LaunchedEffect(key1 = mediaPermissionState.status) {
        if (!mediaPermissionState.status.isGranted) {
            // If permission is not granted, request it.
            // The mediaPermissionState will internally handle showing the system dialog.
            mediaPermissionState.launchPermissionRequest()
        }
        // Update the ViewModel's UI flag based on the current permission status.
        // This ensures that after a permission request, or on initial load with permission
        // already granted, the UI reflects the correct state.
        viewModel.showUi.value = mediaPermissionState.status.isGranted
    }

    // Collect the UI state from the ViewModel.
    val showUi = viewModel.showUi.collectAsState()

    // This is the main UI content of the App composable.
    if (showUi.value) {
        HomeScreen(navController = navController)
    } else {
        // Permission not granted, show appropriate message.
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            val textToShow = when (val currentStatus = mediaPermissionState.status) {
                is PermissionStatus.Granted ->
                    // This state should typically mean showUi.value is true.
                    // If seen, it's likely a brief moment before recomposition.
                    "Permission granted, loading content..."
                is PermissionStatus.Denied -> {
                    if (currentStatus.shouldShowRationale) {
                        "Video permission is crucial for this app. Please grant the permission to allow video playback."
                    } else {
                        // Not granted, and no rationale (either first time, or "don't ask again").
                        "Video permission was denied. To use the app, please enable it in the app settings."
                    }
                }
            }
            Text(text = textToShow)
        }
    }
}
