package com.example.duo.presentation.MainScreen


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.duo.R
import com.example.duo.navigation.Screen

@Composable
fun MainScreen(
    onNavigateTo:(Screen) -> Unit
) {
    Text(text = stringResource(R.string.app_name))
}