package me.yihtseu.messenger.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fileEntity: FileEntity)

    @Delete
    suspend fun delete(fileEntity: FileEntity)

    @Query("SELECT * FROM files WHERE uri = :uri")
    suspend fun queryFile(uri: String): FileEntity?

    @Query("SELECT * FROM files")
    suspend fun queryAllFiles(): LiveData<List<FileEntity>>
}