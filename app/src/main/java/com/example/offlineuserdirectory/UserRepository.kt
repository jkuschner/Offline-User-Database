package com.example.offlineuserdirectory

import kotlinx.coroutines.flow.Flow
import java.io.IOException
import android.util.Log

class UserRepository(
    private val userDao: UserDao,
    private val apiService: UserApiService
) {
    private val TAG = "UserRepository"

    fun getUsersFlow(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun refreshUsers() {
        try {
            // Fetch fresh data from API
            Log.d(TAG, "Attempting to fetch fresh users from API...")
            val newUsers = apiService.getUsers()

            // If successful, update the local database
            userDao.insertAll(newUsers)
            Log.d(TAG, "Successfully fetched and cached ${newUsers.size} users.")

        } catch (e: IOException) {
            Log.e(TAG, "Network refresh failed, showing cached data: ${e.message}")
        } catch (e: Exception) {
            Log.e(TAG, "An unexpected error occurred during refresh: ${e.message}")
        }
    }

    fun searchUsers(searchText: String): Flow<List<User>> {
        val searchPattern = "%$searchText%"
        return userDao.searchUsers(searchPattern)
    }
}