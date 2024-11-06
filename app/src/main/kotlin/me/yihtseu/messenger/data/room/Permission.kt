package me.yihtseu.messenger.data.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
enum class Permission : Parcelable {
    BANNED, ADMIN, COMMON
}