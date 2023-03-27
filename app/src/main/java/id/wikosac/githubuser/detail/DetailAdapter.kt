package id.wikosac.githubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.wikosac.githubuser.R
import id.wikosac.githubuser.main.ItemsItem

class DetailAdapter(
    private val listFollowers: List<ItemsItem>
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView = view.findViewById(R.id.username)
        val avatar: ImageView = view.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val username = listFollowers[position].login
        val avatar = listFollowers[position].avatarUrl
        Glide.with(holder.itemView.context)
            .load(avatar)
            .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).override(200,200))
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(holder.avatar)
        holder.username.text = username
    }

    override fun getItemCount() = listFollowers.size
}