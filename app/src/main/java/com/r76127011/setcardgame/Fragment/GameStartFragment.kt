package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.r76127011.setcardgame.R

/**
 * A simple [Fragment] subclass.
 * Use the [GameStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameStartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_start,null)
        val startButton: Button = view.findViewById(R.id.start_game_button)
        startButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_gameStartFragment_to_gamePlayFragment)
        }
        return view
    }


}