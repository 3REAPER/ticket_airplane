package ru.pervukhin.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ru.pervukhin.presentation.R
import ru.pervukhin.presentation.loadImage

class SearchAdapter(val onClick: (SearchObject) -> Unit = {}) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var data = listOf<SearchObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = data[position]

        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val town = holder.itemView.findViewById<TextView>(R.id.town)

        when (item.id) {
            1 -> image.setImageResource(R.drawable.first_search)
            2 -> image.setImageResource(R.drawable.second_search)
            3 -> image.setImageResource(R.drawable.third_search)
            else -> image.setImageResource(R.drawable.third_search)
        }

        town.text = item.town

        holder.itemView.setOnClickListener {
            onClick.invoke(item)
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<SearchObject>) {
        this.data = data
        notifyDataSetChanged()
    }

    class SearchViewHolder(view: View) : ViewHolder(view)
}