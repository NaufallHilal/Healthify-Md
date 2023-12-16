package com.naufalhilal.healthifyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naufalhilal.healthifyapp.data.entity.EatTime

@Dao
interface EatTimeDao {
    @Insert
    fun insertEatTime(eatTime: EatTime)

    @Update
    fun updateEatTime(eatTime: EatTime)

    @Delete
    fun deleteEatTime(eatTime: EatTime)

    @Query("SELECT * FROM eat_time WHERE eat_time_id = :id")
    fun getEatTimeById(id: Int): EatTime

    @Query("SELECT * FROM eat_time WHERE diary_id = :diaryId")
    fun getEatTimesByDiaryId(diaryId: Int): List<EatTime>
}