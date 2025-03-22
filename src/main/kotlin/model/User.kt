package org.example.model

data class User(
    val login: String,
    val followers: Int,
    val following: Int,
    val created_at: String,
    val public_repos: Int
)
