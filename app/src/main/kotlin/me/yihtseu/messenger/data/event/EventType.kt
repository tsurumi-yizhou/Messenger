package me.yihtseu.messenger.data.event

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
enum class EventType : Parcelable {
    MESSAGE, CLIENT
}