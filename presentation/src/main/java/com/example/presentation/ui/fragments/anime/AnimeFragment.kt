package com.example.presentation.ui.fragments.anime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.databinding.FragmentAnimeBinding
import com.example.presentation.state.UIState
import com.example.presentation.ui.adapters.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : Fragment() {

    private lateinit var binding: FragmentAnimeBinding
    private val viewModel: AnimeViewModel by viewModels()
    private val animeAdapter = AnimeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupSubscribes()
    }

    private fun initialize() {
        binding.animeRecView.adapter = animeAdapter
    }

    private fun setupSubscribes() {
        subscribeToFetchAnime()
    }

    private fun subscribeToFetchAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.animeState.collect {
                    when (it) {
                        is UIState.Error -> {
                            binding.progressBar.isVisible = false
                        }
                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is UIState.Success -> {
                            binding.progressBar.isVisible = false
                            animeAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }
}