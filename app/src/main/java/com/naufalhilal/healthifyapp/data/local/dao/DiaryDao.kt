package com.naufalhilal.healthifyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.local.entity.Diary

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diary: Diary)

    @Update
    fun updateDiary(diary: Diary)

    @Delete
    fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary")
    fun getAllDiaries(): List<Diary>

    @Query("SELECT * FROM diary WHERE diary_id = :id")
    fun getDiaryById(id: Int): Diary

}