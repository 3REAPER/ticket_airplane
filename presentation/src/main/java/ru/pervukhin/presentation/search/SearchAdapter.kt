package ru.pervukhin.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.databinding.ItemSearchBinding

class SearchAdapter(val onClick: (SearchObject) -> Unit = {}) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var data = listOf<SearchObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = data[position]

        when (item.id) {
            1 -> holder.binding.image.setImageResource(R.drawable.first_search)
            2 -> holder.binding.image.setImageResource(R.drawable.second_search)
            3 -> holder.binding.image.setImageResource(R.drawable.third_search)
            else -> holder.binding.image.setImageResource(R.drawable.third_search)
        }

        holder.binding.town.text = item.town

        holder.binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<SearchObject>) {
        this.data = data
        notifyDataSetChanged()
    }

    class SearchViewHolder(val binding: ItemSearchBinding) : ViewHolder(binding.root)
}