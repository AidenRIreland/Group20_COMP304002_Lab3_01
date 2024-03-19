package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group20_comp304002_lab3_01.databinding.FragmentDetailedScheduleBinding
import kotlinx.coroutines.launch


class DetailedScheduleFragment : Fragment() {
    companion object {
        var STOP_NAME = "stopName"
    }

    private var _binding: FragmentDetailedScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var stopName: String

    private val viewModel: AirlineScheduleViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

   /** override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }*/
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       arguments?.let { bundle ->
           stopName = DetailedScheduleFragmentArgs.fromBundle(bundle).stopName
       }
   }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailedScheduleFragmentArgs.fromBundle(requireArguments())
        val stopName = args.stopName
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val AirlineStopAdapter = AirlineStopAdapter({})
        // by passing in the stop name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = AirlineStopAdapter
        lifecycle.coroutineScope.launch {
            viewModel.getByStopName(stopName).collect { listOfSchedules ->
                AirlineStopAdapter.submitList(listOfSchedules)
                // If your Schedule object has a status field, you could do:
                val statusText = if (listOfSchedules.isNotEmpty()) listOfSchedules[0].status else "Status not available"
                // Then update a TextView with this statusText
                // Assuming you have a TextView in your layout for status
                binding.statusText.text = statusText
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}