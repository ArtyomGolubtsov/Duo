package com.example.duo.presentation.MainScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.duo.R
import com.example.duo.data.PrefSetting.setFirstLaunchApp
import com.example.duo.navigation.Screen

@Composable
fun MainScreen(
    onNavigateTo:(Screen) -> Unit
) {
    val context = LocalContext.current
    Text(text = stringResource(R.string.app_name), modifier = Modifier.padding(40.dp).clickable{setFirstLaunchApp(
        context = context
    )})

}