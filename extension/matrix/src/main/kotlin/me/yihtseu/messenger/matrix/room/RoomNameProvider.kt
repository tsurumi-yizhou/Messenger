package me.yihtseu.messenger.matrix.room

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.matrix.android.sdk.api.provider.RoomDisplayNameFallbackProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomNameProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : RoomDisplayNameFallbackProvider {
    override fun excludedUserIds(roomId: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun getNameFor1member(name: String): String {
        TODO("Not yet implemented")
    }

    override fun getNameFor2members(name1: String, name2: String): String {
        TODO("Not yet implemented")
    }

    override fun getNameFor3members(name1: String, name2: String, name3: String): String {
        TODO("Not yet implemented")
    }

    override fun getNameFor4members(
        name1: String,
        name2: String,
        name3: String,
        name4: String
    ): String {
        TODO("Not yet implemented")
    }

    override fun getNameFor4membersAndMore(
        name1: String,
        name2: String,
        name3: String,
        remainingCount: Int
    ): String {
        TODO("Not yet implemented")
    }

    override fun getNameForEmptyRoom(
        isDirect: Boolean,
        leftMemberNames: List<String>
    ): String {
        TODO("Not yet implemented")
    }

    override fun getNameForRoomInvite(): String {
        TODO("Not yet implemented")
    }
}