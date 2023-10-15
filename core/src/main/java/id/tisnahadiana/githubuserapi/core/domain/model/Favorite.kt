package id.tisnahadiana.githubuserapi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String
): Parcelable
