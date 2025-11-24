package com.example.demo_slide.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ImageResultData(
    val id: String?,
    val url: String?,
)