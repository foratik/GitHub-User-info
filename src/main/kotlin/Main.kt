package org.example

import org.example.api.RetrofitClient
import org.example.cache.CacheManager
import org.example.model.User
import org.example.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

fun main() {
    while (true) {
        println(
            """
            |📦 Program Menu:
            |1️⃣ Get user information by username
            |2️⃣ Show list of cached users
            |3️⃣ Search for a user in the cache by username
            |4️⃣ Search for a repository by name
            |5️⃣ Exit
            """.trimMargin()
        )

        when (readlnOrNull()) {
            "1" -> fetchUser()
            "2" -> showCachedUsers()
            "3" -> searchUserInCache()
            "4" -> searchRepoInCache()
            "5" -> exitProcess(0)
            else -> println("❌ Invalid option, please try again.")
        }
    }
}

fun fetchUser() {
    print("👤 GitHub username: ")
    val username = readlnOrNull() ?: return

    CacheManager.getUser(username)?.let {
        println("✅ Data retrieved from cache:")
        println(it)
        return
    }

    RetrofitClient.instance.getUser(username).enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    CacheManager.saveUser(username, it)
                    println("✅ Data received:")
                    println(it)
                    fetchRepos(username)
                }
            } else {
                println("❌ Error fetching data: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            println("❌ Request failed: ${t.message}")
        }
    })
}

fun fetchRepos(username: String) {
    RetrofitClient.instance.getRepos(username).enqueue(object : Callback<List<Repo>> {
        override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    CacheManager.saveRepos(username, it)
                    println("📚 Repository list:")
                    it.forEach { repo -> println("- ${repo.name}") }
                }
            } else {
                println("❌ Error fetching repositories: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
            println("❌ Request failed: ${t.message}")
        }
    })
}

fun showCachedUsers() {
    val users = CacheManager.getAllUsers()
    if (users.isEmpty()) {
        println("📭 Cache is empty.")
    } else {
        println("📜 Cached users:")
        users.forEach { println("- ${it.login}") }
    }
}

fun searchUserInCache() {
    print("🔍 Username: ")
    val username = readlnOrNull() ?: return
    CacheManager.getUser(username)?.let {
        println("✅ User information:")
        println(it)
    } ?: println("❌ User not found in cache.")
}

fun searchRepoInCache() {
    print("🔎 Repository name: ")
    val repoName = readlnOrNull() ?: return
    val allRepos = CacheManager.getAllUsers().flatMap { user ->
        CacheManager.getRepos(user.login) ?: emptyList()
    }

    val matchingRepos = allRepos.filter { it.name.contains(repoName, ignoreCase = true) }
    if (matchingRepos.isNotEmpty()) {
        println("✅ Found repositories:")
        matchingRepos.forEach { repo -> println("- ${repo.name} (${repo.html_url})") }
    } else {
        println("❌ No repository found with this name.")
    }
}
