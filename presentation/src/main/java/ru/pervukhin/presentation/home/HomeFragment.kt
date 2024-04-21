package ru.pervukhin.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Layout.Directions
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
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.FragmentHomeBinding
import ru.pervukhin.presentation.search.SearchBottomSheet


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

        binding.etFrom.setText(viewModel.getCityFrom())

        binding.etFrom.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.saveCityFrom(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

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
                        if (!it.isNullOrEmpty()){
                            adapter.setData(it)
                        }
                    }
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}