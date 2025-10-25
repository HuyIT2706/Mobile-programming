package com.example.BaiTap_Tuan1.BaiTap_Tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Screen3(
    selectedItem: String,
    onNavigateBackToScreen1: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Chi tiết Item",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
        
        // Card hiển thị item được chọn
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Item đã chọn:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF666666)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedItem,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2)
                )
            }
        }
        
        // Nội dung chi tiết
        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F9FA)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Thông tin chi tiết",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Đây là màn hình chi tiết của $selectedItem. " +
                            "Tại đây bạn có thể xem thông tin đầy đủ về item đã chọn. " +
                            "Màn hình này được tạo để demo navigation trong Jetpack Compose.\n\n" +
                            "Tính năng:\n" +
                            "• Hiển thị item được chọn từ Screen2\n" +
                            "• Navigation giữa các màn hình\n" +
                            "• Button quay lại Screen1\n" +
                            "• UI đẹp mắt với Material Design\n\n" +
                            "Bạn có thể thêm nhiều thông tin chi tiết khác tại đây như:\n" +
                            "• Mô tả sản phẩm\n" +
                            "• Giá cả\n" +
                            "• Đánh giá\n" +
                            "• Hình ảnh\n" +
                            "• Và nhiều thông tin khác...",
                    fontSize = 14.sp,
                    color = Color(0xFF333333),
                    lineHeight = 20.sp
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Thông tin bổ sung
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E8)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "💡 Lưu ý:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Đây là demo navigation trong Jetpack Compose. " +
                                    "Bạn có thể customize màn hình này theo nhu cầu của ứng dụng.",
                            fontSize = 14.sp,
                            color = Color(0xFF2E7D32),
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Button quay lại Screen1
        Button(
            onClick = onNavigateBackToScreen1,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2)
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Quay lại Screen 1",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen3Preview() {
    Screen3(
        selectedItem = "Item 123456",
        onNavigateBackToScreen1 = {}
    )
}

