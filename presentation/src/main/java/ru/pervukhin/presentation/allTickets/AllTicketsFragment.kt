package ru.pervukhin.presentation.allTickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.FragmentAllTicketsBinding

@AndroidEntryPoint
class AllTicketsFragment : Fragment() {
    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllTicketsViewModel by viewModels()
    private val adapter = TicketsAdapter()
    private val args: AllTicketsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(layoutInflater)

        setUI()
        setCollectors()

        viewModel.getTickets()

        return binding.root
    }

    private fun setUI() {
        binding.rvTickets.adapter = adapter

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvRoute.text = getString(R.string.route, args.from, args.to)
        binding.tvDate.text = getString(R.string.date, args.date)
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tickets.collect {
                        if (!it.isNullOrEmpty()) {
                            adapter.setData(it)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}