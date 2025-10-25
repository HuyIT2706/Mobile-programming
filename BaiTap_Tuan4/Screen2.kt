package com.example.BaiTap_Tuan1.BaiTap_Tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun Screen2(
    onNavigateToScreen3: (String) -> Unit
) {
    var selectedItem by remember { mutableStateOf("") }
    var showLazyColumn by remember { mutableStateOf(false) }
    var showColumn by remember { mutableStateOf(false) }

    val items = remember {
        (1..1000000).map { "Item $it" }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "LazyVolumn và Column",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { 
                    showLazyColumn = !showLazyColumn
                    showColumn = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (showLazyColumn) Color(0xFF4CAF50) else Color(0xFF2196F3)
                )
            ) {
                Text("LazyColumn")
            }
            
            Button(
                onClick = { 
                    showColumn = !showColumn
                    showLazyColumn = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (showColumn) Color(0xFF4CAF50) else Color(0xFF2196F3)
                )
            ) {
                Text("Column")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Hiển thị thông tin đã chọn
        if (selectedItem.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Text(
                    text = "Selected: $selectedItem",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        // Hiển thị LazyColumn
        if (showLazyColumn) {
            Text(
                text = "LazyColumn (1,000,000 items)",
                fontSize = 14.sp,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items) { item ->
                    Card(
                        onClick = { 
                            selectedItem = item
                            onNavigateToScreen3(item)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F5F5)
                        )
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
        
        // Hiển thị Column thường
        if (showColumn) {
            Text(
                text = "Column (1,000,000 items) ",
                fontSize = 14.sp,
                color = Color(0xFFFF9800),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items.forEach { item ->
                    Card(
                        onClick = { 
                            selectedItem = item
                            onNavigateToScreen3(item)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F5F5)
                        )
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen2Preview() {
    Screen2(
        onNavigateToScreen3 = {}
    )
}
