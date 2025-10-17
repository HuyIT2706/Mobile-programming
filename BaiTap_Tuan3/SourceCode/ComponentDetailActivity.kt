package com.example.BaiTap_Tuan1.BaiTap_Tuan3.SourceCode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class ComponentDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val key = intent.getStringExtra(EXTRA_KEY) ?: ""
        setContent { DetailScreen(key = key, onBack = { finish() }) }
    }

    companion object {
        const val EXTRA_KEY = "component_key"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(key: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = titleFor(key), color = Color(0xFF27A7E7), fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text(text = "<", color = Color(0xFF27A7E7), fontSize = 20.sp)
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (key) {
                "Text" -> TextDetail()
                "Image" -> ImageDetail()
                "TextField" -> TextFieldDetail()
                "PasswordField" -> TextFieldDetail(isPassword = true)
                "Row" -> RowDetail()
                "Column" -> ColumnDetail()
                else -> Text(text = "No detail available")
            }
        }
    }
}

private fun titleFor(key: String): String = when (key) {
    "Text" -> "Text Detail"
    "Image" -> "Images"
    "TextField" -> "TextField"
    "PasswordField" -> "PasswordField"
    "Row" -> "Row Layout"
    "Column" -> "Column Layout"
    else -> key
}

@Composable
private fun TextDetail() {
    val text = buildAnnotatedString {
        append("The ")
        withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick ") }
        withStyle(SpanStyle(color = Color(0xFFB47416), fontWeight = FontWeight.Bold)) { append("Brown\n") }
        append("fox j u m p s ")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("over\n") }
        append("the ")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("lazy ") }
        append("dog.")
    }
    Text(text = text, fontSize = 22.sp, modifier = Modifier.fillMaxWidth())
    Spacer(Modifier.height(16.dp))
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)), modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(1.dp))
    }
}

@Composable
private fun ImageDetail() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = "https://giaothongvantai.hcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
            contentDescription = "Truong giao thong van tai TPHCM",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.height(16.dp))
        Text(text = "https://giaothongvantai.hcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png", color = Color(0xFF2F76C9), fontSize = 12.sp)
        Spacer(Modifier.height(16.dp))
        Image(
            painter = painterResource(id = com.example.bt1.R.drawable.gtvt),
            contentDescription = "Truong giao thong van tai TPHCM",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Trường giao thông vận tải TPHCM", color = Color(0xFF6B6B6B))
    }
}

@Composable
private fun TextFieldDetail(isPassword: Boolean = false) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text,
        onValueChange = { newValue -> text = newValue },
        placeholder = { Text(text = if (isPassword) "Mật khẩu" else "Thông tin nhập") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (passwordVisible) "Ẩn" else "Hiện",
                        tint = Color(0xFF6B6B6B)
                    )
                }
            }
        } else null
    )
    if (!isPassword) {
        Spacer(Modifier.height(12.dp))
        Text(text = text, color = Color(0xFFD9534F), style = TextStyle(fontSize = 12.sp))
    }
}

@Composable
private fun RowDetail() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        repeat(4) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                repeat(3) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = CardDefaults.cardColors(containerColor = if (it == 1) Color(0xFF6AAEF5) else Color(0xFFCDE3F7)),
                        shape = RoundedCornerShape(12.dp)
                    ) {}
                }
            }
        }
    }
}

@Composable
private fun ColumnDetail() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        repeat(5) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFCDE3F7)),
                shape = RoundedCornerShape(12.dp)
            ) {}
        }
    }
}


