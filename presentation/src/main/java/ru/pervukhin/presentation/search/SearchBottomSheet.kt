package ru.pervukhin.presentation.search

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.BottomSheetSearchBinding


class SearchBottomSheet: BottomSheetDialogFragment() {
    private var _binding: BottomSheetSearchBinding? = null
    private val binding get() = _binding!!
    private val args: SearchBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSearchBinding.inflate(layoutInflater)

        val adapter = SearchAdapter{
            binding.etTo.setText(it.town)
        }

        binding.rvSearch.adapter = adapter
        adapter.setData(listOf(SearchObject(1, "Стамбул"), SearchObject(2, "Сочи"), SearchObject(3, "Пхукет")))

        binding.hardRoute.setOnClickListener {
            val action = SearchBottomSheetDirections.actionSearchBottomSheetToHardRouteFragment()
            findNavController().navigate(action)
        }

        binding.anyPlace.setOnClickListener {
            binding.etTo.setText(getString(R.string.any_place))
        }

        binding.weekend.setOnClickListener {
            val action = SearchBottomSheetDirections.actionSearchBottomSheetToWeekendFragment()
            findNavController().navigate(action)
        }

        binding.hotTicket.setOnClickListener {
            val action = SearchBottomSheetDirections.actionSearchBottomSheetToHotTicketFragment()
            findNavController().navigate(action)
        }

        binding.etFrom.setText(args.from)

        binding.etTo.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val action = SearchBottomSheetDirections.actionSearchBottomSheetToSearchTicketsFragment(binding.etFrom.text.toString(), binding.etTo.text.toString())
                findNavController().navigate(action)
            }
        })

        binding.ivClose.setOnClickListener {
            binding.etFrom.setText("")
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let {view ->
                val behaviour = BottomSheetBehavior.from(view)
                setupFullHeight(view)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getWindowHeight()
        bottomSheet.layoutParams = layoutParams
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme
}