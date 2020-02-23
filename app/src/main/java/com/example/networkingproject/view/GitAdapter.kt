package com.example.networkingproject.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkingproject.R
import com.example.networkingproject.model.Repository.RepositoryResponse
import kotlinx.android.synthetic.main.git_item.view.*

class GitAdapter(var repoList: List<RepositoryResponse>) : RecyclerView.Adapter<GitAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.git_item, parent, false)))

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.populateItem(repoList[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun populateItem(repo : RepositoryResponse)
        {
            itemView.tvName.text = repo.full_name
            itemView.tvUrl.text = repo.url
            itemView.tvIsPrivate.text = "Is this repo private? " + repo.private
        }

    }
}
