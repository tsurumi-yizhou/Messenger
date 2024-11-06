package me.yihtseu.messenger.data.user

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun queryUserById(id: String): UserEntity?

    @Query("SELECT * FROM users WHERE name LIKE :keyword OR nickname LIKE :keyword")
    suspend fun queryUsersByKeyword(keyword: String): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM users")
    suspend fun queryAllUsers(): PagingSource<Int, UserEntity>
}