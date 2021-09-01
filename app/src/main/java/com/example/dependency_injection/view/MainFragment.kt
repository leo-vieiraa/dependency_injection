package com.example.dependency_injection.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dependency_injection.R
import com.example.dependency_injection.adapter.GithubUsersAdapter
import com.example.dependency_injection.databinding.MainFragmentBinding
import com.example.dependency_injection.model.GithubUser
import com.example.dependency_injection.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val adapter : GithubUsersAdapter = GithubUsersAdapter()

    private val observeUsers = Observer<List<GithubUser>>() {
        adapter.refresh(it)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        binding = MainFragmentBinding.bind(view)

        viewModel.users.observe(viewLifecycleOwner, observeUsers)
        viewModel.fetchUsers()

        val recyclerView = view.findViewById<RecyclerView>(R.id.userListRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }


}