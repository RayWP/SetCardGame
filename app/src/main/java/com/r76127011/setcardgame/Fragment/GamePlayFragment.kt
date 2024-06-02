package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.ViewModel.GameViewModel

class GamePlayFragment : Fragment() {

    companion object {
        fun newInstance() = GamePlayFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_play, container, false)
    }

}