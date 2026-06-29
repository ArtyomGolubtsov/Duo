package com.example.duo.presentation.Qwestion

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Modifier
import android.graphics.BitmapFactory
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigatorExtras
import com.example.duo.R
import com.example.duo.data.PrefSetting.setDateOfRelationship
import com.example.duo.data.PrefSetting.setUserInfo
import com.example.duo.navigation.AppNavigation
import com.example.duo.navigation.BottomMenu
import com.example.duo.navigation.Screen
import com.example.duo.presentation.components.CalendarPickDate
import java.time.LocalDate
import com.example.duo.presentation.Qwestion.Users
import java.lang.Thread.sleep


@Composable
fun QwestionScreens(
    onNavigateTo: (Screen) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable {
            onNavigateTo(Screen.firstQwestionScreen)
        }
        .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
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
    val dateState = remember { mutableStateOf<LocalDate?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (showDialog) {
        CalendarPickDate(
            closeSelection = { showDialog = false }, selectedDate = dateState
        )
    }
    val dateString = dateState.value?.let { date ->
        "${date.dayOfMonth}.${date.monthValue}.${date.year}"
    } ?: "Выбрать дату"

    val isDateSelected = dateState.value != null
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Когда вы стали парой?\uD83D\uDC9E",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 46.sp,
            lineHeight = 46.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )

        Text(text = dateString,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 28.sp,
            lineHeight = 38.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true }
                .padding(36.dp),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center)

        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                setDateOfRelationship(context, dateString)
                onNavigateTo(Screen.secondQwestionScreen)
            },
            enabled = dateString != "Выбрать дату",
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(isDateSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary.copy(0.2f)
            )

        ) {
            Text(
                text = "Продолжить",
                color = if(isDateSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary.copy(0.4f),
                fontSize = 28.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                overflow = TextOverflow.Visible,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun QwestionSecond(
    onNavigateTo: (Screen) -> Unit,
    qwestionViewModel: QwestionViewModel = viewModel()
) {
    val state by qwestionViewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Познакомимся поближе\uD83D\uDE0A",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 44.sp,
            lineHeight = 44.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Две формы, работающие через колбэки во ViewModel
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FormUser(
                    user = state.user1,
                    onNameChange = qwestionViewModel::onUser1NameChange,
                    onSexChange = qwestionViewModel::onUser1SexChange,
                    onDateChange = qwestionViewModel::onUser1DateChange,
                    onPhotoChange = qwestionViewModel::onUser1PhotoChange,
                    qwestionViewModel = qwestionViewModel
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                FormUser(
                    user = state.user2,
                    onNameChange = qwestionViewModel::onUser2NameChange,
                    onSexChange = qwestionViewModel::onUser2SexChange,
                    onDateChange = qwestionViewModel::onUser2DateChange,
                    onPhotoChange = qwestionViewModel::onUser2PhotoChange,
                    qwestionViewModel = qwestionViewModel,
                )
            }
        }

        // Кнопка "Продолжить"
        Button(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            onClick = {
                if (qwestionViewModel.isDataValid) {
                    setUserInfo(context, state.user1)
                    setUserInfo(context, state.user2)
                    onNavigateTo(Screen.Main)
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (qwestionViewModel.isDataValid)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.secondary.copy(0.2f)
            )
        ) {
            Text(
                text = "Продолжить",
                color = if (qwestionViewModel.isDataValid)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.primary.copy(0.4f),
                fontSize = 28.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                overflow = TextOverflow.Visible,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = state.errorMessage,
            color = if (qwestionViewModel.isDataValid)
                MaterialTheme.colorScheme.background
            else
                MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 16.dp),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FormUser(
    user: Users,
    onNameChange: (String) -> Unit,
    onSexChange: (Boolean) -> Unit,
    onDateChange: (String) -> Unit,
    onPhotoChange: (String) -> Unit,
    qwestionViewModel: QwestionViewModel,
) {
    var showDialog by remember { mutableStateOf(false) }
    val dateState = remember { mutableStateOf<LocalDate?>(null) }

    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {uri: Uri? ->
        uri?.let {
            val savedPath = qwestionViewModel.saveImageToInternalStorage (context, it)
            onPhotoChange(savedPath)
        }
    }

    if (showDialog) {
        CalendarPickDate(
            closeSelection = {
                showDialog = false
                dateState.value?.let { date ->
                    val formatted = "${date.dayOfMonth}.${date.monthValue}.${date.year}"
                    onDateChange(formatted)
                }
            },
            selectedDate = dateState
        )
    }
    val dateOfBirthday = user.dateOfBitrh.ifBlank { "Выбрать дату" }

    val photoBitmap = remember (user.photoPath){
        if(user.photoPath.isNotBlank()){
            BitmapFactory.decodeFile(user.photoPath)
        } else null
    }

    val imageBitmap = photoBitmap?.asImageBitmap()
        ?: if (user.sex) ImageBitmap.imageResource(R.drawable.placeholder_man) else ImageBitmap.imageResource(
            R.drawable.placeholder_woman
        )

    Column(
        modifier = Modifier
            .padding(10.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "Иконка человека",
            modifier = Modifier
                .clip(RoundedCornerShape(250.dp))
                .clickable { photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                ) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пол:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (user.sex) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                    .clickable { onSexChange(true) }
                    .padding(4.dp)
            ) {
                Text(text = "Мужчина", color = MaterialTheme.colorScheme.onPrimary, fontSize = 15.sp)
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (!user.sex) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                    .clickable { onSexChange(false) }
                    .padding(4.dp)
            ) {
                Text(text = "Девушка", color = MaterialTheme.colorScheme.onPrimary, fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Имя:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = user.name,
            onValueChange = onNameChange,
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дата рождения:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(0.dp))
        Text(
            text = dateOfBirthday,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true }
                .padding(36.dp),
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
    }
}