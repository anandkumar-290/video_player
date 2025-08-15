package com.example.videoplayer.Presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.Data.model.VideoFile
import com.example.videoplayer.Domain.repo.VideoFileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val repo: VideoFileRepo, val application: Application): ViewModel(){

    val showUi = MutableStateFlow(false)
    val videoList = MutableStateFlow(emptyList<VideoFile>())
    val folderList = MutableStateFlow(emptyMap<String,List<VideoFile>>())

    val isLoading = MutableStateFlow(false)

    fun LoadVideoFiles(){

        isLoading.value = true
        viewModelScope.launch {
            repo.getAllVideos(application).collectLatest {
                videoList.value = it
            }
        }
        isLoading.value = false

    }
    fun LoadVideofolder(){

        isLoading.value = true
        viewModelScope.launch {
            repo.getAllFolders(application).collectLatest {

                folderList.value = it
            }
        }
        isLoading.value = false
    }
    init {
        viewModelScope.launch {
            LoadVideofolder()
            LoadVideoFiles()
        }
    }
}