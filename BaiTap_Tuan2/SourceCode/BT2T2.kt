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

open class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT1Theme {
                CheckEmail()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckEmail() {
    var inputText by remember { mutableStateOf("") }
    var ischeck by remember { mutableStateOf<Boolean?>(null) }
    var message by remember { mutableStateOf("") }

    // Hàm xử lý email
    fun handleCheckEmail() {
        if (inputText.isBlank()) {
            ischeck = false
            message = "Email không hợp lệ"
            return
        }

        val hasAtSign = inputText.contains('@')
        if (hasAtSign) {
            ischeck = true
            message = "Email đúng định dạng"
        } else {
            ischeck = false
            message = "Email không đúng định dạng"
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TextField để nhập số
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Nhập email") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9E9E9E),
                    unfocusedBorderColor = Color(0xFF9E9E9E)
                )
            )
        }
        // Button Tạo
        Button(
            onClick = { handleCheckEmail() },
            modifier = Modifier
                .padding(start = 8.dp),
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
                    .padding(bottom = 16.dp, top = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSuccess) Color(0xFF4CAF50) else Color(0xFFFFEBEE)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = message,
                    color = if (isSuccess) Color.White else Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun checkEmailScreen() {
    BT1Theme {
        CheckEmail()
    }
}