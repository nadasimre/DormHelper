package com.example.dormhelper.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dormhelper.model.User
import com.example.dormhelper.model.DormPreset
import com.example.dormhelper.model.UserAndDormPreset

@Dao
interface UserDatabaseDAO {
    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertDorm(dorm: DormPreset)

    @Update
    suspend fun update(user: User)

    @Update
    suspend fun updateDorm(dorm: DormPreset)

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    suspend fun get(username: String, password: String): User?

    @Query("SELECT * FROM dorm_table WHERE id = :key")
    suspend fun getDorm(key: Int): DormPreset

    @Query("SELECT * FROM dorm_table WHERE id = :key")
    fun getDormById(key: Int): LiveData<DormPreset>

    @Transaction
    @Query("SELECT * FROM user_table")
    fun getUsersAndDorms(): List<UserAndDormPreset>

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("DELETE FROM dorm_table WHERE nameOfUser = :username")
    suspend fun clearDorms(username: String)

    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): User?

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM dorm_table ORDER BY id DESC")
    fun getAllDorms(): LiveData<List<DormPreset>>

    @Query("SELECT * FROM dorm_table WHERE nameOfUser =:username ORDER BY id DESC")
    fun getDorms(username: String): LiveData<List<DormPreset>>

    @Query("SELECT * FROM dorm_table WHERE nameOfUser =:username ORDER BY id DESC")
    suspend fun getUsersDorms(username: String): List<DormPreset>

    @Query("DELETE FROM dorm_table WHERE id = :id")
    suspend fun deleteDorm(id: Int)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun deleteUser(id: Int)
}