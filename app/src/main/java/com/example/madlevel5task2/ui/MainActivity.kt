package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        // TODO: Toolbar magic
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        class MyUndoListener(games: List<Game>?) : View.OnClickListener {

            val games2 = games
            override fun onClick(v: View) {
                games2?.forEach {gameViewModel.addGame(it)
                }
            }
        }

        return when (item.itemId) {
            R.id.delete_all -> {
                val games = gameViewModel.gamesLiveData.value
                gameViewModel.deleteAllGames()
                val snackbar = Snackbar.make(toolbar,
                        "Games deleted", Snackbar.LENGTH_SHORT)
                snackbar.setAction("Undo", MyUndoListener(games))
                snackbar.show()
                return true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        onCreateOptionsMenu(toolbar.menu)
    }

    fun setToolbarWithBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    fun setToolbarWithoutBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setHomeButtonEnabled(false)
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}