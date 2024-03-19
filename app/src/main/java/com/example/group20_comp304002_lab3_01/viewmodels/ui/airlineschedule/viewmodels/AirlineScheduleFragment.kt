package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group20_comp304002_lab3_01.databinding.FragmentAirlineScheduleBinding
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController

class AirlineScheduleFragment : Fragment() {
    private var _binding: FragmentAirlineScheduleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AirlineScheduleViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirlineScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val airlineStopAdapter = AirlineStopAdapter { schedule ->
            val action = AirlineScheduleFragmentDirections
                .actionAirlineScheduleFragmentToDetailedScheduleFragment(schedule.stopName)
            findNavController().navigate(action)
        }

        recyclerView.adapter = airlineStopAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fullSchedule().collect { schedules ->
                airlineStopAdapter.submitList(schedules)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}