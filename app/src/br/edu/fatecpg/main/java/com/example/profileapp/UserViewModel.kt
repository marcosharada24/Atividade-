package com.example.profileapp

import androidx.lifecycle.*

class UserViewModel(private val repo: UserRepository) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        // initial load will be performed by caller using repo.getUser()
    }

    fun setUser(u: User) { _user.value = u }

    fun loadFromRepo() { _user.value = repo.getUser() }

    fun updateUser(name: String, email: String, photoUri: String?) {
        val updated = User(name = name, email = email, photoUri = photoUri)
        _user.value = updated
        repo.saveUser(updated)
    }

    class Factory(private val repo: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
