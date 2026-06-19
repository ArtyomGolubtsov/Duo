package com.example.duo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duo.presentation.KalendarScreen.KalendarScreen

import com.example.duo.presentation.MainScreen.MainScreen
import com.example.duo.presentation.Qwestion.QwestionFirst
import com.example.duo.presentation.Qwestion.QwestionScreens
import com.example.duo.presentation.UserScreen.UserScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Main: Screen()

    @Serializable
    data object kalendar: Screen()

    @Serializable
    data object UserSetting: Screen()

    @Serializable
    data object Qwestions: Screen()

    @Serializable
    data object firstQwestionScreen: Screen()

}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        modifier = Modifier,
        startDestination =  Screen.Qwestions,
    ){
        composable<Screen.Main> {
            MainScreen { onNavigateTo -> navHostController.navigate(onNavigateTo) }
        }

        composable<Screen.UserSetting> {
            UserScreen{ onNavigateTo -> navHostController.navigate(onNavigateTo) }
        }

        composable<Screen.kalendar> {
            KalendarScreen{ onNavigateTo -> navHostController.navigate(onNavigateTo) }
        }

        composable<Screen.firstQwestionScreen> { backStackEntry ->
            QwestionFirst { onNavigateTo ->
                navHostController.navigate(onNavigateTo) {
                    popUpTo(backStackEntry.id) { inclusive = true }
                }
            }
        }

        composable<Screen.Qwestions> { backStackEntry ->
            QwestionScreens { onNavigateTo ->
                navHostController.navigate(onNavigateTo) {
                    popUpTo(backStackEntry.id) { inclusive = true }
                }
            }
        }
    }

}