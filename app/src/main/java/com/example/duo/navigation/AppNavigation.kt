package com.example.duo.navigation

import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duo.presentation.MainScreen.MainScreen
import com.example.duo.presentation.qwestion.QwestionScreens
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
sealed class Screen {
    @Serializable
    data object Main: Screen()

    @Serializable
    data object Qwestions: Screen()


}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        modifier = Modifier,
        startDestination = Screen.Main,
    ){
        composable<Screen.Main> {
            MainScreen { onNavigateTo -> navHostController.navigate(onNavigateTo) }
        }

        composable<Screen.Qwestions> {
            QwestionScreens { onNavigateTo -> navHostController.navigate(onNavigateTo) }
        }
    }

}