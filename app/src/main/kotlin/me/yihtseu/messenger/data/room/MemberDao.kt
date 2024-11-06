package me.yihtseu.messenger.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memberEntity: MemberEntity)

    @Delete
    suspend fun delete(memberEntity: MemberEntity)

    @Query("SELECT * FROM members WHERE room = :roomId")
    suspend fun queryByGroup(roomId: String): LiveData<List<MemberEntity>>
}