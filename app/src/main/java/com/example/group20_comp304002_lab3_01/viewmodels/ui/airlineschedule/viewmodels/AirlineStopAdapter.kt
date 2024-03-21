package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.viewmodels

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.group20_comp304002_lab3_01.databinding.ItemAirlineScheduleBinding
import com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AirlineStopAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, AirlineStopAdapter.AirlineStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AirlineStopViewHolder {
        val binding = ItemAirlineScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirlineStopViewHolder(binding, onItemClicked)
    }override fun onBindViewHolder(holder: AirlineStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirlineStopViewHolder(
        private var binding: ItemAirlineScheduleBinding,
        private val onItemClicked: (Schedule) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        // Inside AirlineStopViewHolder class
        fun bind(schedule: Schedule) {
            // Assuming arrivalTime is in seconds, you need to convert it to milliseconds
            val date = Date(schedule.arrivalTime * 1000L)
            val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val formattedDate = format.format(date)

            binding.apply {
                textViewStopName.text = schedule.stopName
                textViewArrivalTime.text = formattedDate // use the formatted date here
                textViewTerminal.text = schedule.terminal
                textViewStatus.text = schedule.status
                root.setOnClickListener { onItemClicked(schedule) }
            }
       /** fun bind(schedule: Schedule) {
            binding.apply {
                textViewStopName.text = schedule.stopName
                textViewArrivalTime.text = schedule.arrivalTime.toString()
                textViewTerminal.text = schedule.terminal
                root.setOnClickListener { onItemClicked(schedule) }
            }
        }
       */

    }
    }
    }
