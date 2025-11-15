package com.example.offlineuserdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import com.example.offlineuserdirectory.UserDatabase
import com.example.offlineuserdirectory.UserRepository
import com.example.offlineuserdirectory.RetrofitClient
import com.example.offlineuserdirectory.UserViewModel
import com.example.offlineuserdirectory.UserViewModelFactory
import com.example.offlineuserdirectory.UserDirectoryScreen

class MainActivity : ComponentActivity() {

    private val database by lazy { UserDatabase.getDatabase(applicationContext) }
    private val repository by lazy {
        UserRepository(database.userDao(), RetrofitClient.apiService)
    }

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val users by viewModel.users.collectAsState()
            val searchText by viewModel.searchText.collectAsState()

            UserDirectoryScreen(
                users = users,
                searchText = searchText,
                onSearchTextChanged = viewModel::setSearchText
            )
        }
    }
}