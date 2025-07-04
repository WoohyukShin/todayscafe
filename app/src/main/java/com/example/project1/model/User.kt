package com.example.project1.model

data class User (
    val uid: String,
    val name: String,
    val followers: List<User>,
    val recommendation: List<CafeInfo>
)
