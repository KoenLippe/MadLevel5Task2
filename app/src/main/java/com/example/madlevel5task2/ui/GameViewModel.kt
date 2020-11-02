package com.example.madlevel5task2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val gameRepository: GameRepository = GameRepository(application.applicationContext)

    val games: List<Game> = gameRepository.getAllGames()
    val gamesLiveData: MutableLiveData<List<Game>> = MutableLiveData(games)


    fun addGame(game: Game) {
        //TODO validation

        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

    //TODO: Delete game

    //TODO: Remove single game

}