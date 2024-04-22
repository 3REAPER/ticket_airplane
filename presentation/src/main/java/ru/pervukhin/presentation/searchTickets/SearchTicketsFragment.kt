package ru.pervukhin.presentation.searchTickets

import android.app.DatePickerDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.pervukhin.presentation.databinding.FragmentSearchTicketsBinding
import ru.pervukhin.presentation.getStringByFormat
import ru.pervukhin.presentation.gone
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class SearchTicketsFragment : Fragment() {
    private var _binding: FragmentSearchTicketsBinding? = null
    private val binding get() = _binding!!
    private val args: SearchTicketsFragmentArgs by navArgs()
    private val viewModel: SearchTicketsViewModel by viewModels()
    private val adapter = SearchTicketAdapter()
    private var dateDeparture = Calendar.getInstance().time
    private var dateArrival: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchTicketsBinding.inflate(layoutInflater)

        setUI()
        setCollectors()

        viewModel.getTicketOffers()

        return binding.root
    }

    private fun setUI() {
        binding.etFrom.setText(args.from)
        binding.etTo.setText(args.to)

        binding.ivClose.setOnClickListener {
            binding.etTo.setText("")
        }

        binding.ivSwop.setOnClickListener {
            val buffer = binding.etFrom.text
            binding.etFrom.setText(binding.etTo.text)
            binding.etTo.text = buffer
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWatchAllTicket.setOnClickListener {
            val action =
                SearchTicketsFragmentDirections.actionSearchTicketsFragmentToAllTicketsFragment(binding.etFrom.text.toString(), binding.etTo.text.toString(), dateDeparture.getStringByFormat("dd MMMM"))
            findNavController().navigate(action)
        }

        binding.llBack.setOnClickListener {
            showStartDatePickerDialog()
        }

        binding.llDate.setOnClickListener {
            showStartDatePickerDialog(true)
        }

        binding.rvTicketOffers.adapter = adapter

        binding.date.text = convertDate(dateDeparture)
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.ticketOffer.collect {
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

    private fun convertDate(date: Date) =
        date.getStringByFormat("d LLL,ccc").replace(".", "")


    private fun showStartDatePickerDialog(isDeparture: Boolean = false) {
        context?.let {
            val calendarPlusDay = Calendar.getInstance()
            calendarPlusDay.add(Calendar.DAY_OF_MONTH, 1)

            val dialog = DatePickerDialog(
                it,
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        val calendar = Calendar.getInstance()
                        calendar.set(
                            p1,
                            p2,
                            p3,
                            0,
                            0,
                            0
                        )
                        calendar.set(Calendar.MILLISECOND, 0)

                        if (isDeparture) {
                            dateDeparture = calendar.time
                            binding.date.text = convertDate(calendar.time)
                        } else {
                            dateArrival = calendar.time
                            binding.tvBack.text = convertDate(calendar.time)
                            binding.ivAdd.gone()
                        }
                    }
                },
                calendarPlusDay.get(Calendar.YEAR),
                calendarPlusDay.get(Calendar.MONTH) - 1,
                calendarPlusDay.get(Calendar.DAY_OF_MONTH)
            )
            if (isDeparture) {
                dateArrival?.let {
                    dialog.datePicker.maxDate = it.time
                }
                dialog.datePicker.minDate = Calendar.getInstance().time.time
            } else {
                dialog.datePicker.minDate = dateDeparture.time
            }

            dialog.show()
        }


    }
}