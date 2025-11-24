package com.example.demo_slide.productdetail

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val PRODUCT_API_BASE_URL = "https://mock.apidog.com/m1/890655-872447-default/v2/product"

// Khởi tạo Ktor HttpClient
private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        })
    }
}

suspend fun getProductDetail(productId: String): Product {
    val url = PRODUCT_API_BASE_URL
    try {
        val response = client.get(url)
        val jsonString = response.bodyAsText()
        
        // Log response để debug (có thể xóa sau)
        println("API Response: $jsonString")
        
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
        
        // Thử parse như object trước
        return try {
            json.decodeFromString<Product>(jsonString)
        } catch (e: Exception) {
            println("Parse as object failed: ${e.message}")
            // Nếu thất bại, thử parse như mảng và lấy phần tử đầu tiên
            try {
                val products: List<Product> = json.decodeFromString<List<Product>>(jsonString)
                products.firstOrNull() ?: throw Exception("No product found in array")
            } catch (e2: Exception) {
                println("Parse as array failed: ${e2.message}")
                throw Exception("Failed to parse product. JSON: $jsonString")
            }
        }
    } catch (e: Exception) {
        throw Exception("API call failed for URL: $url. Error: ${e.message}")
    }
}
