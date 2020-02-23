package com.example.networkingproject.model.User

data class UserResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)