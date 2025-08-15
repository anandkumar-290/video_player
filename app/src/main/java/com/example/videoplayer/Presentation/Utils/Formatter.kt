package com.example.videoplayer.Presentation.Utils

import java.text.SimpleDateFormat
import java.util.Locale


fun FormatDuration(durationInMillis : Long) : String {

    val seconds = (durationInMillis/1000).toInt()
    val minutes = seconds/60
    val hours = minutes/60
    return when{

        hours > 0 -> String.format("%d:%02d:%02d",hours,minutes%60,seconds%60)
        else -> String.format("%d:%02d",minutes,seconds % 60)

    }
}

fun FormatFileSize(sizeInBytes : Long): String{

    val kb = sizeInBytes / 1024.0
    val mb = kb/1024.0
    val gb = mb/1024.0

    return when{
        gb >= 1 -> "%.2f GB".format(gb)
        mb >= 1 -> "%.2f MB".format(mb)
        kb >= 1 -> "%.2f KB".format(kb)

        else -> "$sizeInBytes bytes"

    }
}

fun FormatDate(timestamp: Long):String{
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(timestamp*1000)
}