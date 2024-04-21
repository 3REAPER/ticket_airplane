package ru.pervukhin.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.pervukhin.domain.Offer
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.loadImage
import ru.pervukhin.presentation.toCurrencyString
import java.time.OffsetTime

class OfferAdapter: RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    private var data = listOf<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = data[position]

        val image: ImageView = holder.itemView.findViewById(R.id.image)
        val title: TextView = holder.itemView.findViewById(R.id.tvTitle)
        val town: TextView = holder.itemView.findViewById(R.id.town)
        val price: TextView = holder.itemView.findViewById(R.id.price)

        val drawableId = when(offer.id){
            1-> R.drawable.first_image
            2-> R.drawable.second_image
            3-> R.drawable.third_image
            else -> R.drawable.third_image
        }

        image.loadImage(drawableId)
        title.text = offer.title
        town.text = offer.town
        price.text = holder.itemView.context.getString(R.string.price).format(offer.price.value.toFloat().toCurrencyString(holder.itemView.context))
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Offer>){
        this.data = data
        notifyDataSetChanged()
    }

    class OfferViewHolder(view: View): ViewHolder(view)
}