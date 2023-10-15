package id.tisnahadiana.githubuserapi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search(
    val totalCount: Int?,
    val incompleteResults: Boolean?,
    val users: List<User>?
): Parcelable
