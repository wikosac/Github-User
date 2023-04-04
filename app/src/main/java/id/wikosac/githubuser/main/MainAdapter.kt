package id.wikosac.githubuser.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.wikosac.githubuser.R

class MainAdapter(
    private val listItems: List<ItemsItem>
    ) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView = view.findViewById(R.id.username)
        val avatar: ImageView = view.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val username = listItems[position].login
        val avatar = listItems[position].avatarUrl
        Glide.with(holder.itemView.context)
            .load(avatar)
            .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).override(200,200))
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(holder.avatar)
        holder.username.text = username
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listItems[holder.adapterPosition])
        }
    }

    override fun getItemCount() = listItems.size
}