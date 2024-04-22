package ru.pervukhin.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.pervukhin.domain.Offer
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.ItemOfferBinding
import ru.pervukhin.presentation.toCurrencyString

class OfferAdapter: RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    private var data = listOf<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = data[position]

        when(offer.id){
            1-> holder.binding.image.setImageResource(R.drawable.first_image)
            2-> holder.binding.image.setImageResource(R.drawable.second_image)
            3-> holder.binding.image.setImageResource(R.drawable.third_image)
            else -> holder.binding.image.setImageResource(R.drawable.third_image)
        }

        holder.binding.tvTitle.text = offer.title
        holder.binding.town.text = offer.town
        holder.binding.price.text = holder.itemView.context.getString(R.string.price).format(offer.price.value.toFloat().toCurrencyString(holder.itemView.context))
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Offer>){
        this.data = data
        notifyDataSetChanged()
    }

    class OfferViewHolder(val binding: ItemOfferBinding): ViewHolder(binding.root)
}