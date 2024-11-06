package me.yihtseu.messenger.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomEntity: RoomEntity)

    @Query("SELECT * FROM rooms WHERE id = :id")
    suspend fun queryById(id: String): RoomEntity?

    @Query("SELECT * FROM rooms WHERE name LIKE '%:keyword%'")
    suspend fun queryByKeyword(keyword: String): LiveData<List<RoomEntity>>
}