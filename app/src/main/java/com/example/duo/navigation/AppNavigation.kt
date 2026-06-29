package com.example.duo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duo.presentation.KalendarScreen.KalendarScreen

import com.example.duo.presentation.MainScreen.MainScreen
import com.example.duo.presentation.MainScreen.MainViewModel
import com.example.duo.presentation.Qwestion.QwestionFirst
import com.example.duo.presentation.Qwestion.QwestionScreens
import com.example.duo.presentation.Qwestion.QwestionSecond
import com.example.duo.presentation.Qwestion.QwestionViewModel
import com.example.duo.presentation.UserScreen.UserAuth.UserAuthScreen
import com.example.duo.presentation.UserScreen.UserScreen
import com.example.duo.presentation.UserScreen.UserViewModel
import com.google.firebase.auth.FirebaseAuth
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

    @Serializable
    data object secondQwestionScreen: Screen()
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    finishOnboarding: () -> Unit,
    startScreen: Screen
) {
    NavHost(
        navController = navHostController,
        modifier = Modifier,
        startDestination =  startScreen,
    ){
        composable<Screen.Main> {
            val mainViewModel: MainViewModel = viewModel()
            MainScreen(mainViewModel = mainViewModel,
                onNavigateTo = { onNavigateTo -> navHostController.navigate(onNavigateTo)})

        }

        composable<Screen.UserSetting> {
            val userViewModel: UserViewModel = viewModel ()
            lateinit var auth: FirebaseAuth
            auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            if (currentUser != null) {
                UserScreen({ onNavigateTo -> navHostController.navigate(onNavigateTo) },userViewModel)
            }
            else{
                UserAuthScreen(
                    { onNavigateTo -> navHostController.navigate(onNavigateTo) },
                    userViewModel
                )
            }

        }

        composable<Screen.kalendar> {
            lateinit var auth: FirebaseAuth
            auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val userViewModel: UserViewModel = viewModel ()
            if (currentUser != null) {
                KalendarScreen{ onNavigateTo -> navHostController.navigate(onNavigateTo) }
            }
            else{
                UserAuthScreen(
                    { onNavigateTo -> navHostController.navigate(onNavigateTo) },
                    userViewModel
                )
            }

        }

        composable<Screen.firstQwestionScreen> { backStackEntry ->
            QwestionFirst { onNavigateTo ->
                navHostController.navigate(onNavigateTo) {
                    popUpTo(backStackEntry.id) { inclusive = true }
                }
            }
        }

        composable<Screen.secondQwestionScreen> { backStackEntry ->
            val qwestionViewModel: QwestionViewModel = viewModel(backStackEntry)
            QwestionSecond ( onNavigateTo = { onNavigateTo ->
                if(onNavigateTo == Screen.Main)
                {
                    finishOnboarding()
                }
                navHostController.navigate(onNavigateTo) {
                    popUpTo(backStackEntry.id) { inclusive = true }
                }
            },
            qwestionViewModel = qwestionViewModel
            )
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