package me.yihtseu.messenger.data.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val avatar: String,
    val name: String,
    val nickname: String,
    val presence: Boolean,
    val lastOnline: Long
) : Parcelable
