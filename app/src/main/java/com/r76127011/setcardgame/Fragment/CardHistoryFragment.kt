package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r76127011.setcardgame.RVAdapter.GamePlayRecyclerViewAdapter
import com.r76127011.setcardgame.RVAdapter.MyCardHistoryRecyclerViewAdapter
import com.r76127011.setcardgame.ViewModel.GameViewModel
import com.r76127011.setcardgame.component.GridSpacingItemDecoration
import com.r76127011.setcardgame.databinding.FragmentCardHistoryBinding
import com.r76127011.setcardgame.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class CardHistoryFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    private lateinit var cardHistoryAdapter: MyCardHistoryRecyclerViewAdapter
    private lateinit var binding: FragmentCardHistoryBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tempViewModel = viewModel
        binding = FragmentCardHistoryBinding.inflate(inflater, container, false)
        cardHistoryAdapter = MyCardHistoryRecyclerViewAdapter()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.addItemDecoration(
            GridSpacingItemDecoration(3, 20, true)
        )
        recyclerView.adapter = cardHistoryAdapter
        viewModel.cardHistory.observe(viewLifecycleOwner) {
            println(it.size)
            cardHistoryAdapter.values = it
        }
        return binding.root
    }
}