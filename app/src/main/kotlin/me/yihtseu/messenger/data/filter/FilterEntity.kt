package me.yihtseu.messenger.data.filter

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "filters")
data class FilterEntity(
    @PrimaryKey
    val id: String,
) : Parcelable
