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
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.time.LocalDate

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        (activity as MainActivity).setToolbarWithBackButton()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddGame.setOnClickListener {
            onAddGameFormSubmit()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle(getString(
            R.string.add_game_title
        ))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun onAddGameFormSubmit() {
        //TODO add checks

        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day: Int = Integer.parseInt(etDay.text.toString())
        val month = Integer.parseInt(etMonth.text.toString())
        val year = Integer.parseInt(etYear.text.toString())

        val date = LocalDate.of(year, month, day)

        val game = Game(title, platform, date)

        gameViewModel.addGame(game)

        (activity as MainActivity).setToolbarWithoutBackButton()
        findNavController().popBackStack()

    }
}