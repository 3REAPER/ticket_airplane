package ru.pervukhin.presentation.allTickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.domain.Ticket
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.ItemTicketBinding
import ru.pervukhin.presentation.getStringByFormat
import ru.pervukhin.presentation.gone
import ru.pervukhin.presentation.toCurrencyString
import ru.pervukhin.presentation.visible
import java.util.Date
import java.util.concurrent.TimeUnit

class TicketsAdapter : RecyclerView.Adapter<TicketsAdapter.TicketViewHolder>() {
    private var data = listOf<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = data[position]

        if (item.badge != null){
            holder.binding.layoutBadge.visible()
            holder.binding.badge.text = item.badge
        }else{
            holder.binding.layoutBadge.gone()
        }

        holder.binding.price.text = item.price.value.toFloat().toCurrencyString(holder.binding.root.context)
        holder.binding.tvTimeArrival.text = item.arrival.date.getStringByFormat("HH:mm")
        holder.binding.tvCodeArrival.text = item.arrival.airport
        holder.binding.tvTimeDeparture.text = item.departure.date.getStringByFormat("HH:mm")
        holder.binding.tvCodeDeparture.text = item.departure.airport

        val transferText = if (item.hasTransfer){
            ""
        }else{
            holder.binding.root.context.getString(R.string.no_transfer)
        }
        holder.binding.tvTransfer.text = holder.binding.root.context.getString(R.string.transfer, TimeUnit.MILLISECONDS.toHours(item.arrival.date.time - item.departure.date.time).toInt().toString(), transferText)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Ticket>){
        this.data = data
        notifyDataSetChanged()
    }

    class TicketViewHolder(val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root)
}