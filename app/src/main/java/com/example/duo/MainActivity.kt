package com.example.duo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.duo.data.PrefSetting.getFirstLaunchApp
import com.example.duo.navigation.AppNavigation
import com.example.duo.navigation.BottomMenu
import com.example.duo.ui.theme.DuoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuoTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                if (!getFirstLaunchApp(context)) {
                    Scaffold(
                        bottomBar = {
                            BottomMenu(navController = navController)
                        }
                    ) { innerPadding ->
                        AppNavigation(
                            navHostController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                } else {
                    Scaffold(
                    ) { innerPadding ->
                        AppNavigation(
                            navHostController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

