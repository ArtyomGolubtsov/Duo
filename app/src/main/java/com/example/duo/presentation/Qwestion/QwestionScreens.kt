package com.example.duo.presentation.Qwestion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigationevent.compose.rememberNavigationEventDispatcherOwner
import com.example.duo.navigation.Screen

@Composable
fun QwestionScreens(
    onNavigateTo: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clickable {
                onNavigateTo(Screen.firstQwestionScreen)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Привет в Duo!\uD83D\uDC4B",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 46.sp,
            lineHeight = 38.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Здесь каждая секунда ваших отношений обретает ценность, ведь любовь — это не пункт назначения, а бесконечное путешествие.",
            color = MaterialTheme.colorScheme.secondary.copy(0.8f),
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 20.dp),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Нажмите чтобы продолжить",
            color = MaterialTheme.colorScheme.secondary.copy(0.2f),
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun QwestionFirst(onNavigateTo: (Screen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clickable {
                onNavigateTo(Screen.firstQwestionScreen)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Когда вы стали парой?\uD83D\uDC9E",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 46.sp,
            lineHeight = 38.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
    }
}