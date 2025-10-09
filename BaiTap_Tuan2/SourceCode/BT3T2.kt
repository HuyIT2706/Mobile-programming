package com.example.BaiTap_Tuan1.BaiTap_Tuan2.SourceCode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt1.ui.theme.BT1Theme

class MainCheckAge : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT1Theme {
                CheckAge()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckAge() {
    var nameText by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }
    var ischeck by remember { mutableStateOf<Boolean?>(null) }
    var message by remember { mutableStateOf("") }

    // Hàm xử lý kiểm tra tuổi
    fun handleCheckAge() {
        if (nameText.isBlank() || ageText.isBlank()) {
            ischeck = false
            message = "Vui lòng nhập đầy đủ tên và tuổi"
            return
        }

        val age = ageText.toIntOrNull()
        if (age == null) {
            ischeck = false
            message = "Tuổi phải là số hợp lệ"
            return
        }

        val category = when {
            age > 65 -> "Người già"
            age in 6..65 -> "Người lớn"
            age in 2..6 -> "Trẻ em"
            age < 2 -> "Em bé"
            else -> "Không xác định"
        }

        ischeck = true
        message = "Tên: $nameText \nTuổi: $age \nPhân loại: $category"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "Kiểm tra tuổi",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp),
            textAlign = TextAlign.Center,
        )

        // Input fields cho tên và tuổi
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // TextField để nhập tên
            OutlinedTextField(
                value = nameText,
                onValueChange = { nameText = it },
                label = { Text("Họ và tên") },
                placeholder = { Text("Nhập họ và tên") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9E9E9E),
                    unfocusedBorderColor = Color(0xFF9E9E9E)
                )
            )
            
            // TextField để nhập tuổi
            OutlinedTextField(
                value = ageText,
                onValueChange = { ageText = it },
                label = { Text("Tuổi") },
                placeholder = { Text("Nhập tuổi") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9E9E9E),
                    unfocusedBorderColor = Color(0xFF9E9E9E)
                )
            )
        }
        // Button Kiểm tra
        Button(
            onClick = { handleCheckAge() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Kiểm tra",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        // Hiển thị kết quả khi đã kiểm tra
        if (ischeck != null) {
            val isSuccess = ischeck == true
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSuccess) Color(0xFFE8F5E8) else Color(0xFFFFEBEE)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = message,
                    fontWeight = FontWeight.Bold,
                    color = if (isSuccess) Color(0xFF2E7D32) else Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(15.dp),
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CheckAgePreview() {
    BT1Theme {
        CheckAge()
    }
}