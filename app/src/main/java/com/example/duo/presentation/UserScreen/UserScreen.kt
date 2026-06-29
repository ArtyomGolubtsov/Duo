package com.example.duo.presentation.UserScreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duo.navigation.Screen
import com.example.duo.presentation.MainScreen.MainScreenState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun UserScreen(    onNavigateTo: (Screen) -> Unit,
                   userViewModel: UserViewModel = viewModel()
) {
    lateinit var auth: FirebaseAuth
    auth = FirebaseAuth.getInstance()

    fun CheckAuth(): Boolean{
        val currentUser = auth.currentUser
        if(currentUser!= null){
            return true
        }
        else return false
    }

}