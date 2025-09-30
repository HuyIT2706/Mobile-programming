package com.example.bt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt1.ui.theme.BT1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT1Theme {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F8FF))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header với nút back và edit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút Back
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF333333),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF333333)
                    )
                }
                
                // Nút Edit
                IconButton(
                    onClick = { /* Handle edit action */ },
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF333333),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color(0xFF333333)
                    )
                }
            }
            
            // Nội dung chính - căn giữa màn hình
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Ảnh profile
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE6F3FF))
                            .padding(4.dp)
                    ) {
                        // Placeholder cho ảnh profile
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color(0xFFB3D9FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "JS",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Tên người dùng
                    Text(
                        text = "Johan Smith",
                        fontSize = 24.sp,
                        color = Color(0xFF333333)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Địa chỉ
                    Text(
                        text = "California, USA",
                        fontSize = 18.sp,
                        color = Color(0xFF666666)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BT1Theme {
        ProfileScreen()
    }
}