package me.yihtseu.messenger.core.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.yihtseu.messenger.data.AppDatabase
import me.yihtseu.messenger.data.event.EventDao
import me.yihtseu.messenger.data.filter.FilterDao
import me.yihtseu.messenger.data.room.RoomDao
import me.yihtseu.messenger.data.user.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "messenger")
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(database: AppDatabase): EventDao {
        return database.eventDao()
    }

    @Provides
    @Singleton
    fun provideFilterDao(database: AppDatabase): FilterDao {
        return database.filterDao()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: AppDatabase): RoomDao {
        return database.roomDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}