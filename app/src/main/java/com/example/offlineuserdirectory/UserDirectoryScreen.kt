package com.example.offlineuserdirectory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDirectoryScreen(
    users: List<User>,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                searchText = searchText,
                onSearchTextChanged = onSearchTextChanged
            )
        }
    ) { paddingValues ->
        UserList(
            users = users,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun SearchAppBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        label = { Text("Search by Name or Email") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun UserList(
    users: List<User>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(users, key = { it.id }) { user ->
            UserListItem(user = user)
            Divider()
        }
    }
}

@Composable
fun UserListItem(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = "**ID:** ${user.id}", style = MaterialTheme.typography.bodySmall)
        Text(text = "**Name:** ${user.name}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "**Email:** ${user.email}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "**Phone:** ${user.phone}", style = MaterialTheme.typography.bodyMedium)
    }
}