package ru.pervukhin.presentation.searchTickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.domain.TicketOffer
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.toCurrencyString

class SearchTicketAdapter : RecyclerView.Adapter<SearchTicketAdapter.SearchTicketViewHolder>() {
    private var data = listOf<TicketOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTicketViewHolder {
        return SearchTicketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_offer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchTicketViewHolder, position: Int) {
        val item = data[position]

        val image: View = holder.itemView.findViewById(R.id.image)
        val name: TextView = holder.itemView.findViewById(R.id.name)
        val price: TextView = holder.itemView.findViewById(R.id.price)
        val time: TextView = holder.itemView.findViewById(R.id.time)

        var timeValue = ""
        item.timeRange.forEach {
            timeValue += it +" "
        }

        val colorId = when(item.tittle){
            "Уральские авиалинии"-> R.color.red
            "Победа"-> R.color.blue
            "NordStar"->R.color.white
            else -> R.color.green
        }

        image.backgroundTintList = ContextCompat.getColorStateList(image.context, colorId)

        name.text = item.tittle
        price.text = item.price.value.toFloat().toCurrencyString(price.context)
        time.text = timeValue
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<TicketOffer>) {
        this.data = data
        notifyDataSetChanged()
    }

    class SearchTicketViewHolder(view: View) : RecyclerView.ViewHolder(view)
}