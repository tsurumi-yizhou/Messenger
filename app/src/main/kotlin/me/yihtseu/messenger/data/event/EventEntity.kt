package me.yihtseu.messenger.data.event

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: String,
    val type: EventType
)
