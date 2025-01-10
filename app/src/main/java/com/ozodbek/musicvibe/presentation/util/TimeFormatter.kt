package com.ozodbek.musicvibe.presentation.util

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun rememberFormattedTimeFromLong(time: Long): String {
    val localtime by remember(time) {
        derivedStateOf {
            val dateTime = Instant.ofEpochMilli(time)
                .atZone(ZoneId.of("Europe/London"))
                .toLocalTime()
            val formatter = DateTimeFormatter.ofPattern("mm:ss")
            dateTime.format(formatter)
        }
    }
    return localtime
}
@SuppressLint("NewApi")
@Composable
fun rememberFormattedTimeFromLong(time: Float): String {
    val localtime by remember(time) {
        derivedStateOf {
            val dateTime = Instant.ofEpochMilli(time.toLong())
                .atZone(ZoneId.of("Europe/London"))
                .toLocalTime()
            val formatter = DateTimeFormatter.ofPattern("mm:ss")
            dateTime.format(formatter)
        }
    }
    return localtime
}

@Preview
@Composable
fun TimeFormatPreview() {
    val time = rememberFormattedTimeFromLong(time = 1000L)
    Text(text = time)
}