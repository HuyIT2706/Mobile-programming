package com.example.BaiTap_Tuan1.BaiTap_Tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationApp()
                }
            }
        }
    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            JetpackComposeOnboarding(
                onNavigateToScreen2 = {
                    navController.navigate("screen2")
                }
            )
        }
        
        composable("screen2") {
            Screen2(
                onNavigateToScreen3 = { selectedItem ->
                    navController.navigate("screen3/$selectedItem")
                }
            )
        }
        
        composable(
            "screen3/{selectedItem}",
            arguments = listOf(
                navArgument("selectedItem") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val selectedItem = backStackEntry.arguments?.getString("selectedItem") ?: ""
            Screen3(
                selectedItem = selectedItem,
                onNavigateBackToScreen1 = {
                    navController.navigate("screen1") {
                        popUpTo("screen1") { inclusive = true }
                    }
                }
            )
        }
    }
}
