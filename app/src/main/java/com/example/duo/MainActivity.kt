package com.example.duo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.duo.data.PrefSetting.getFirstLaunchApp
import com.example.duo.data.PrefSetting.setFirstLaunchApp
import com.example.duo.navigation.AppNavigation
import com.example.duo.navigation.BottomMenu
import com.example.duo.navigation.Screen
import com.example.duo.ui.theme.DuoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuoTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                var isFirstLaunch by remember{
                    mutableStateOf(getFirstLaunchApp(context))
                }

                val finishOnboarding: () -> Unit = {
                    setFirstLaunchApp(context, false) // нужно добавить этот метод
                    isFirstLaunch = false
                }

                if (!isFirstLaunch) {
                    Scaffold(
                        bottomBar = {
                            BottomMenu(navController = navController)
                        }
                    ) { innerPadding ->
                        AppNavigation(
                            navHostController = navController,
                            modifier = Modifier.padding(innerPadding),
                            finishOnboarding = finishOnboarding,
                            startScreen = Screen.Main
                        )
                    }
                } else {
                    Scaffold(
                    ) { innerPadding ->
                        AppNavigation(
                            navHostController = navController,
                            modifier = Modifier.padding(innerPadding),
                            finishOnboarding = finishOnboarding,
                            startScreen = Screen.Qwestions
                        )
                    }
                }
            }
        }
    }
}

