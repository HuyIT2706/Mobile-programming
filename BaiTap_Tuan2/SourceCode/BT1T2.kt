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

class MainCheckMail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT1Theme {
                RedBoxScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedBoxScreen() {
    var inputText by remember { mutableStateOf("") }
    var numberBox  by remember { mutableStateOf(0) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Hàm xử lý khi nhấn button Tạo
    fun handleCreateButton() {
        if (inputText.isEmpty()) {
            numberBox = 0
            showError = true
            errorMessage = "Vui lòng nhập số lượng"
            return
        }

        // Kiểm tra xem input có phải là số không
        try {
            val number = inputText.toInt()
            if (number > 0) {
                numberBox = number
                showError = false

            } else {
                numberBox = 0
                showError = true
                errorMessage = "Vui lòng nhập số lớn hơn 0"
            }
        } catch (e: NumberFormatException) {
            // Nếu không phải số, hiển thị lỗi
            numberBox = 0
            showError = true
            errorMessage = "Dòng nhập sai dữ liệu - Vui lòng nhập số nguyên"
        }
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
            text = "Thực hành 02",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp),
            textAlign = TextAlign.Center,
        )

        // Box chứa input và button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TextField để nhập số
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Nhập vào số lượng") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9E9E9E),
                    unfocusedBorderColor = Color(0xFF9E9E9E)
                )
            )
            
            // Button Tạo
            Button(
                onClick = { handleCreateButton() },
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Tạo",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        // Hiển thị thông báo lỗi
        if (showError) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFEBEE)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Hiển thị các ô đỏ
        if (numberBox > 0 && !showError) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(numberBox) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Red),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RedBoxScreenPreview() {
    BT1Theme {
        RedBoxScreen()
    }
}