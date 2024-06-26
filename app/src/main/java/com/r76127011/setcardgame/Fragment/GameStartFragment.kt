package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.databinding.FragmentGameStartBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameStartFragment : Fragment() {

    private lateinit var binding: FragmentGameStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameStartBinding.inflate(inflater, container, false)
        val startButton: Button = binding.startGameButton
        startButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_gameStartFragment_to_gameContainerFragment)
        }
        return binding.root
    }


}