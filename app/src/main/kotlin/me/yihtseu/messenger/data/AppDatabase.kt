package me.yihtseu.messenger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.yihtseu.messenger.data.event.EventDao
import me.yihtseu.messenger.data.event.EventEntity
import me.yihtseu.messenger.data.filter.FilterDao
import me.yihtseu.messenger.data.filter.FilterEntity
import me.yihtseu.messenger.data.room.RoomDao
import me.yihtseu.messenger.data.room.RoomEntity
import me.yihtseu.messenger.data.user.UserDao
import me.yihtseu.messenger.data.user.UserEntity

@Database(
    entities = [
        EventEntity::class,
        FilterEntity::class,
        RoomEntity::class,
        UserEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun filterDao(): FilterDao
    abstract fun userDao(): UserDao
    abstract fun roomDao(): RoomDao
}