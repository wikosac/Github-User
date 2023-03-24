package id.wikosac.githubuser

import android.content.ClipData.Item
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter(
    private val listItems: List<ItemsItem>
    ) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val selectionIds = ArrayList<String>()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(
                oldData: ItemsItem, newData: ItemsItem
            ): Boolean {
                return oldData.id == newData.id
            }
            override fun areContentsTheSame(
                oldData: ItemsItem, newData: ItemsItem
            ): Boolean {
                return oldData == newData
            }
        }
        private const val TAG = "UserAdapter"
    }

    interface ClickHandler {
        fun onClick(position: Int, ItemsItem: ItemsItem)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val username: TextView = view.findViewById(R.id.username)
        val avatar: ImageView = view.findViewById(R.id.avatar)

//        fun bind(items: ItemsItem) = with(binding) {
//            username.text = items.login
//            Glide.with(avatar.context)
//                .load(items.avatarUrl)
//                .error(R.drawable.ic_baseline_broken_image_24)
//                .into(avatar)

//            val pos = absoluteAdapterPosition
//            itemView.setOnClickListener { handler.onClick(pos, items) }

//            itemView.setOnClickListener {
//                val intent = Intent(root.context, DetailActivity::class.java)
//                intent.putExtra("toko", food.toko)
//                intent.putExtra("alamat", food.alamat)
//                intent.putExtra("rating", food.rating)
//                intent.putExtra("img", PlaceApi.getPlaceUrl(food.toko))
//                root.context.startActivity(intent)
//            }
//        }
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
    }

    override fun getItemCount() = listItems.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun toggleSelection(pos: Int) {
//        val id = getItem(pos).id
//        if (selectionIds.contains(id))
//            selectionIds.remove(id)
//        else
//            selectionIds.add(id)
//        notifyDataSetChanged()
//    }
//
//    fun getSelection(): List<String> {
//        return selectionIds
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun resetSelection() {
//        selectionIds.clear()
//        notifyDataSetChanged()
//    }

}