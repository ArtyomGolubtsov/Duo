package com.example.duo.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.duo.R


sealed class BottomItem(val title: String, val iconId: Int, val route: Screen)
{
    object MainScreen: BottomItem("Главный экран", R.drawable.ic_main, Screen.Main)

    object SettingScreen: BottomItem("Настройки", R.drawable.ic_user, Screen.UserSetting)

    object CalendarScreen: BottomItem("Календарь", R.drawable.ic_kalendar, Screen.kalendar)
}

@Composable
fun BottomMenu(
    navController: NavController
) {
    val listItems = listOf(BottomItem.MainScreen, BottomItem.CalendarScreen, BottomItem.SettingScreen)

    NavigationBar(
        modifier = Modifier
            .height(130.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color.Black)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listItems.forEach { item ->
            val isSelected = currentRoute == item.route::class.qualifiedName
            NavigationBarItem(
                selected = currentRoute == item.route.toString(),
                onClick = {
                    navController.navigate(item.route) { launchSingleTop = true }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    )
                },

            )
        }
    }
}

