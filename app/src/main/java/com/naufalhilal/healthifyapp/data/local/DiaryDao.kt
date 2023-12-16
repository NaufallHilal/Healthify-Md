package com.naufalhilal.healthifyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.entity.Diary

@Dao
interface DiaryDao {
    @Insert
    fun insertDiary(diary: Diary)

    @Update
    fun updateDiary(diary: Diary)

    @Delete
    fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary WHERE diary_id = :id")
    fun getDiaryById(id: Int): Diary

    @Query("SELECT * FROM diary WHERE user_id = :userId")
    fun getDiariesByUserId(userId: Int): List<Diary>
}