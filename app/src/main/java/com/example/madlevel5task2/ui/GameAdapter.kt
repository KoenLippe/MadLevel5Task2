package com.example.madlevel5task2.ui

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.ItemGameBinding
import com.example.madlevel5task2.model.Game

class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemGameBinding.bind(itemView)


        @RequiresApi(Build.VERSION_CODES.O)
        fun dataBind(game: Game) {
            binding.txtTitle.text = game.title
            binding.txtPlatform.text = game.platform
//            binding.txtRelease.text =
//                String.format("Release: %s %s %s",
//                    game.date.dayOfMonth,
//                    game.date.month,
//                    game.date.year)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position])
    }
}