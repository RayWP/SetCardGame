package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.r76127011.setcardgame.RVAdapter.GamePlayRecyclerViewAdapter
import com.r76127011.setcardgame.ViewModel.GameViewModel
import com.r76127011.setcardgame.component.GridSpacingItemDecoration
import com.r76127011.setcardgame.databinding.FragmentGamePlayBinding

class GamePlayFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    private lateinit var gamePlayAdapter: GamePlayRecyclerViewAdapter
    private lateinit var binding: FragmentGamePlayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        //give space between item
        binding.recyclerView.addItemDecoration(
            GridSpacingItemDecoration(3, 10, true)
        )
        binding.recyclerView.adapter = GamePlayRecyclerViewAdapter(viewModel.onscreenDeck.value!!)
        return binding.root
    }

}