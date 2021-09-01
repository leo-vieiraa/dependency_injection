package com.example.dependency_injection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependency_injection.model.GithubUser
import com.example.dependency_injection.model.GithubUserResponse
import com.example.dependency_injection.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<GithubUser>>()
    val users : LiveData<List<GithubUser>> = _users

    fun fetchUsers() {
        repository.fetchUsers() { response, _ ->
            response?.let { resp ->
                _users.value = resp.items
            }
        }
    }

}