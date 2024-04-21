package ru.pervukhin.presentation.searchTickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.domain.TicketOffer
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.ItemTicketOfferBinding
import ru.pervukhin.presentation.toCurrencyString

class SearchTicketAdapter : RecyclerView.Adapter<SearchTicketAdapter.SearchTicketViewHolder>() {
    private var data = listOf<TicketOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTicketViewHolder {
        return SearchTicketViewHolder(
            ItemTicketOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchTicketViewHolder, position: Int) {
        val item = data[position]

        var timeValue = ""
        item.timeRange.forEach {
            timeValue += it + " "
        }

        val colorId = when (item.tittle) {
            "Уральские авиалинии" -> R.color.red
            "Победа" -> R.color.blue
            "NordStar" -> R.color.white
            else -> R.color.green
        }

        holder.binding.image.backgroundTintList = ContextCompat.getColorStateList(holder.binding.root.context, colorId)

        holder.binding.name.text = item.tittle
        holder.binding.price.text = item.price.value.toFloat().toCurrencyString(holder.binding.root.context)
        holder.binding.time.text = timeValue
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<TicketOffer>) {
        this.data = data
        notifyDataSetChanged()
    }

    class SearchTicketViewHolder(val binding: ItemTicketOfferBinding) :
        RecyclerView.ViewHolder(binding.root)
}