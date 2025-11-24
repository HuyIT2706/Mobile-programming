package com.example.demo_slide.productdetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(kotlinx.serialization.InternalSerializationApi::class)
@Serializable 
data class Product(
    val id: String? = null,
    val name: String? = null,
    @SerialName("imgURL")
    val image: String? = null,
    @SerialName("des")
    val description: String? = null,
    val price: Double? = null,
    val createdAt: String? = null
)
