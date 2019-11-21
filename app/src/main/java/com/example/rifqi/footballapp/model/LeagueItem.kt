package com.example.rifqi.footballapp.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class LeagueItem(
    var name: String?,
    var image: Int?,
    val id: String?
): Parcelable