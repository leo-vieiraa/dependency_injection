package com.example.dependency_injection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dependency_injection.R
import com.example.dependency_injection.databinding.ItemUserBinding
import com.example.dependency_injection.model.GithubUser

class GithubUsersAdapter : RecyclerView.Adapter<GithubUsersViewHolder>() {

    private val userList = mutableListOf<GithubUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return GithubUsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        userList[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun refresh(newList: List<GithubUser>) {
        userList.addAll(newList)
        notifyDataSetChanged()
    }

}

class GithubUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name = view.findViewById<TextView>(R.id.nameTextView)

    fun bind(user: GithubUser) {

        name.text = user.login

    }

}