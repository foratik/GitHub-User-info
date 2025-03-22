package org.example.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import org.example.model.User
import org.example.model.Repo

interface GitHubApi {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<List<Repo>>
}
