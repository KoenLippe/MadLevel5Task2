package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_overview.*
import java.time.LocalDate
import androidx.lifecycle.Observer
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OverviewFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private val games = arrayListOf<Game>()
    @RequiresApi(Build.VERSION_CODES.O)
    private val gameAdapter = GameAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabOverview.setOnClickListener {
            findNavController().navigate(R.id.action_OverviewFragment_to_AddGameFragment)
        }


        initRv()
        observeLiveData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRv() {
        rvGames.apply {
            adapter = gameAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeLiveData() {
        gameViewModel.gamesLiveData.observe(viewLifecycleOwner, Observer { liveGames: List<Game> ->
            games.clear()
            games.addAll(liveGames)
            gameAdapter.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle(getString(R.string.overview_title))
    }
}