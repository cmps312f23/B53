package com.cmps312.newshub.entity

import kotlinx.serialization.Serializable

@Serializable
data class Category (
    val category: String,
    val id: Int = 0

)