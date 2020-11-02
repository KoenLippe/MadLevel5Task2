package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import com.example.madlevel5task2.model.Game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

}