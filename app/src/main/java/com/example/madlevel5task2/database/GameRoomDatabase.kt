package com.example.madlevel5task2.database

import com.example.madlevel5task2.model.Game
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GameDao

@TypeConverters(Converters::class)
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameRoomDatabase: RoomDatabase() {

    abstract fun getGameDao(): GameDao

    companion object {
        private val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if(gameDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if(gameDatabaseInstance == null) {
                        gameDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return gameDatabaseInstance
        }
    }


}