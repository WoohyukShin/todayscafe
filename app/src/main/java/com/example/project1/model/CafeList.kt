package com.example.project1.model

data class CafeList (
    val cid: String,
    val name: String = "",
    val imageURL: Int,
    val list: List<CafeInfo> = emptyList()
)