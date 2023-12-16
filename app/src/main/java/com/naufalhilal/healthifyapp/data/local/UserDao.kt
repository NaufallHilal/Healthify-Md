package com.naufalhilal.healthifyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE user_id = :id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): User?
}
