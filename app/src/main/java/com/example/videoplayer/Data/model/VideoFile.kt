package com.example.videoplayer.Data.model

data class VideoFile(
    var id : String,
    var path : String?,
    var title : String,
    var fileName : String,
    var size : String,
    var duration : String,
    var dateAdded : String,
    var thumbnailUrl : String? = null

)