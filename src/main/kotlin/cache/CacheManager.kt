package org.example.cache

import org.example.model.User
import org.example.model.Repo

object CacheManager {
    private val userCache = mutableMapOf<String, User>()
    private val repoCache = mutableMapOf<String, List<Repo>>()

    fun saveUser(username: String, user: User) {
        userCache[username] = user
    }

    fun getUser(username: String): User? = userCache[username]

    fun saveRepos(username: String, repos: List<Repo>) {
        repoCache[username] = repos
    }

    fun getRepos(username: String): List<Repo>? = repoCache[username]

    fun getAllUsers(): List<User> = userCache.values.toList()
}
