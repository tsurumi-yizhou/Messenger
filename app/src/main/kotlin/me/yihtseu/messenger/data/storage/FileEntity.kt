package me.yihtseu.messenger.data.storage

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey
    val uri: String,
    val name: String,
    val size: Long,
    val mime: String,
    val expire: Long
): Parcelable
