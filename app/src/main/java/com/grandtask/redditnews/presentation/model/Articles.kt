package com.grandtask.redditnews.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Articles(
    val data: Data,
)

@Parcelize
data class DataItem(
    val id: String,
    val name: String,
    val selftext: String,
    val thumbnail: String,
    val title: String,
    val url: String,
    val media: SecureMedia?
) : Parcelable

@Parcelize
data class SecureMedia(
    val oembed: Oembed,
) : Parcelable

@Parcelize
data class Oembed(
    val thumbnail_url: String,
) : Parcelable

data class Data(
    val children: List<Children>,
)

data class Children(
    val data: DataItem,
)