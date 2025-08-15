package com.example.videoplayer.Presentation.VideoPlayerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text // Added for the error message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController

@Composable
fun VideoPlayerScreen(videoUrl: String?, navController: NavHostController) { // navController is currently unused
    
    val context = LocalContext.current

    // ExoPlayer instance will be created and remembered only if videoUrl is not null.
    // It's keyed to nnVideoUrl, so if the url changes, ExoPlayer is re-initialized.
    val exoPlayerInstance = videoUrl?.let { nnVideoUrl ->
        remember(nnVideoUrl) {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(nnVideoUrl) // nnVideoUrl is non-null here
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (exoPlayerInstance != null) {
            // Only compose PlayerView and its DisposableEffect if exoPlayerInstance is valid
            DisposableEffect(
                // The key for DisposableEffect is the AndroidView instance itself.
                // When this AndroidView (bound to a specific exoPlayerInstance) leaves composition,
                // onDispose will be called to release that player.
                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = exoPlayerInstance
                        }
                    },
                    update = {
                        // This ensures the player is correctly set if the view is reused
                        // with a different player instance (though less likely with current keying).
                        it.player = exoPlayerInstance
                    },
                    modifier = Modifier.fillMaxSize() // Make the player view fill the available space
                )
            ) {
                onDispose {
                    exoPlayerInstance.release()
                }
            }
        } else {
            // videoUrl was null, so exoPlayerInstance is null. Show a message.
            Text("Video URL is not available.")
        }
    }
}
