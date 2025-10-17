package com.example.BaiTap_Tuan1.BaiTap_Tuan3.SourceCode

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainBT2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { ComponentsListScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "UI Components List", color = Color(0xFF27A7E7)) })
        }
    ) { innerPadding ->
        val context = LocalContext.current
        val groups = listOf(
            "Display" to listOf(
                "Text" to "Displays text",
                "Image" to "Displays an image"
            ),
            "Input" to listOf(
                "TextField" to "Input field for text",
                "PasswordField" to "Input field for passwords"
            ),
            "Layout" to listOf(
                "Column" to "Arranges elements vertically",
                "Row" to "Arranges elements horizontally"
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(groups) { (groupTitle, items) ->
                GroupSection(title = groupTitle, items = items) { key ->
                    val intent = Intent(context, ComponentDetailActivity::class.java)
                    intent.putExtra(ComponentDetailActivity.EXTRA_KEY, key)
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
private fun GroupSection(title: String, items: List<Pair<String, String>>, onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF6B6B6B),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items.forEach { (name, desc) ->
                ComponentRow(title = name, description = desc, onClick = { onItemClick(name) })
            }
        }
    }
}

@Composable
private fun ComponentRow(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6ECFB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold, color = Color(0xFF267EBE))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF4C4C4C),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComponentsListScreenPreview() {
    ComponentsListScreen()
}