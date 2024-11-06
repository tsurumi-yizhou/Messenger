package me.yihtseu.messenger.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.*

@Parcelize
@Serializable
@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey
    val id: String,
    val icon: String,
    val name: String,
    val nickname: String,
    val shortcutId: String = UUID.randomUUID().toString(),
    val notificationId: String = UUID.randomUUID().toString()
) : Parcelable
