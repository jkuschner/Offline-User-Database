package com.example.offlineuserdirectory

import retrofit2.http.GET

interface UserApiService {
    // Defines a GET request to the "/users" path relative to the base URL.
    @GET("users")
    // 'suspend' keyword makes this a Coroutine function, allowing safe background execution.
    suspend fun getUsers(): List<User>
}