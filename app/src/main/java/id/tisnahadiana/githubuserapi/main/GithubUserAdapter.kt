package id.tisnahadiana.githubuserapi.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.core.domain.model.User
import id.tisnahadiana.githubuserapi.databinding.ItemUserBinding

class GithubUserAdapter : RecyclerView.Adapter<GithubUserAdapter.UserViewHolder>() {

    private val list = ArrayList<User>()
    private val diffCallback = UserDiffCallback()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(newList: List<User>) {
        val result = DiffUtil.calculateDiff(diffCallback.calculateDiff(newList, list))
        list.clear()
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userResponse: User) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(userResponse)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(userResponse.avatarUrl)
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .into(avatarImage)
                usernameText.text = userResponse.login
                urlText.text = userResponse.htmlUrl
            }


        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    inner class UserDiffCallback : DiffUtil.Callback() {
        private lateinit var newList: List<User>
        private lateinit var oldList: List<User>

        fun calculateDiff(newList: List<User>, oldList: List<User>): UserDiffCallback {
            this.newList = newList
            this.oldList = oldList
            return this
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}

