package com.example.demo_slide.productdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String = "1") { // Lấy sản phẩm có ID = 1 làm mặc định
    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Sử dụng LaunchedEffect để gọi API một lần khi màn hình được tạo
    LaunchedEffect(productId) {
        isLoading = true
        error = null
        product = null
        try {
            val result = withContext(Dispatchers.IO) {
                getProductDetail(productId)
            }
            product = result
            // Kiểm tra nếu product không có dữ liệu thực sự
            if (result.id == null && result.name == null) {
                error = "Không tìm thấy dữ liệu sản phẩm"
            }
        } catch (e: Exception) {
            error = "Lỗi tải dữ liệu sản phẩm: ${e.message}"
            e.printStackTrace() // Log lỗi để debug
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý quay lại */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = error!!,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                product != null -> {
                    // Khi có dữ liệu, hiển thị giao diện chi tiết
                    ProductContentView(product = product!!)
                }
                else -> {
                    Text(
                        text = "Không có dữ liệu sản phẩm",
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductContentView(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tên sản phẩm
        Text(
            text = product.name ?: "No name",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Giá sản phẩm
        Text(
            text = "$${String.format("%.2f", product.price ?: 0.0)}",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFE57373) // Màu đỏ nhẹ
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mô tả sản phẩm
        Text(
            text = product.description ?: "No description available",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Khối chứa hình ảnh - đã di chuyển xuống dưới
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.image ?: "",
                contentDescription = product.name ?: "Product image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống dưới cùng

        // Nút Add to cart
        Button(
            onClick = { /* Xử lý thêm vào giỏ hàng */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(
                text = "Add to cart",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
