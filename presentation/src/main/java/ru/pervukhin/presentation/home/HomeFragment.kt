package ru.pervukhin.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.pervukhin.presentation.databinding.FragmentHomeBinding
import ru.pervukhin.presentation.gone
import ru.pervukhin.presentation.invisible
import ru.pervukhin.presentation.visible


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = OfferAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        setUI()
        setCollectors()

        viewModel.getOffers()

        return binding.root
    }
    private fun setUI() {
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvOffers.layoutManager = manager
        binding.rvOffers.adapter = adapter

        binding.etTo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchBottomSheet(binding.etFrom.text.toString())
            findNavController().navigate(action)
        }


    }


    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.offers.collect{
                        it.ifSuccess {
                            binding.titleMusic.visible()
                            binding.rvOffers.visible()
                            binding.progressBar.gone()
                            if (!it.isNullOrEmpty()) {
                                adapter.setData(it)
                            }
                        }.ifError {
                            binding.error.visible()
                            binding.progressBar.gone()
                            binding.titleMusic.gone()
                            binding.rvOffers.gone()
                        }.ifLoading {
                            binding.progressBar.visible()
                            binding.titleMusic.gone()
                            binding.rvOffers.gone()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etFrom.setText(viewModel.getCityFrom())
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCityFrom(binding.etFrom.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}