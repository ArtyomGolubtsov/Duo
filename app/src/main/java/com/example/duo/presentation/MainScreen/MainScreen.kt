package com.example.duo.presentation.MainScreen


import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duo.R
import com.example.duo.data.PrefSetting.getDateOfRelationship
import com.example.duo.data.PrefSetting.getUserInfo
import com.example.duo.data.PrefSetting.setDateOfRelationship
import com.example.duo.data.PrefSetting.setFirstLaunchApp
import com.example.duo.navigation.Screen
import com.example.duo.presentation.Qwestion.Users

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit, mainViewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by mainViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        val user1 = getUserInfo(context, true)
        val user2 = getUserInfo(context, false)
        mainViewModel.setUsers(user1 = user1, user2 = user2)
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            userPlaceHolderView(
                user = state.user1,
                mainViewModel.calculateDate(state.user1.dateOfBitrh, false)
            )
            userPlaceHolderView(
                user = state.user2, mainViewModel.calculateDate(state.user2.dateOfBitrh, false)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${getDateOfRelationship(context).toString()} - дата вашей любви\uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(8.dp),
                overflow = TextOverflow.Visible,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(start = 16.dp, end = 4.dp)
                        .height(20.dp), gapSize = 0.dp, progress = {
                        mainViewModel.calculateProgress(
                            getDateOfRelationship(context).toString(),

                            )
                    })
                Text(
                    text = mainViewModel.dateRoSh(
                        mainViewModel.calculateDate(
                            getDateOfRelationship(context).toString(),
                            true
                        ).toLong()
                    ).toString(),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(vertical = 4.dp),
                    overflow = TextOverflow.Visible,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "${
                    mainViewModel.calculateDate(
                        getDateOfRelationship(context).toString(),
                        true
                    )
                } ${mainViewModel.correctDays(                    mainViewModel.calculateDate(
                    getDateOfRelationship(context).toString(),
                    true
                ).toInt())} вместе!",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(
                        mainViewModel.calculateProgress(
                            getDateOfRelationship(context).toString(),
                        )
                    ),
                overflow = TextOverflow.Visible,
                textAlign = TextAlign.Center
            )


        }
        Spacer(modifier = Modifier.height(8.dp))
        val index = 50
        val daysTowife = mainViewModel.calculateDate(getDateOfRelationship(context).toString(),true)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(index){
                index->
                val flag4 = if (index <= 6) (index*10).toString() else if(index in 7..9) ((index-6)*100).toString() else ((index-9)*365).toString()
                val dateFlag = mainViewModel.calculateProgress(
                    getDateOfRelationship(context).toString(), flag4.toInt()

                    )
                Row(
                    modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(start = 16.dp, end = 4.dp)
                            .height(20.dp), gapSize = 0.dp, progress = {
                            dateFlag

                        },
                        color = if(dateFlag >= 1.0) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${flag4}",
                        color = if(dateFlag == 1f) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(vertical = 4.dp),
                        overflow = TextOverflow.Visible,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = if(dateFlag == 1f) "Вы достигли этой даты: ${mainViewModel.nDaysAgo(daysTowife.toInt() - flag4.toInt())}" else "Вам осталось: ${flag4.toInt() - daysTowife.toInt()} ${mainViewModel.correctDays(flag4.toInt() - daysTowife.toInt())}",
                    color = if(dateFlag == 1f) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(
                            mainViewModel.calculateProgress(
                                getDateOfRelationship(context).toString(),
                            )
                        ),
                    overflow = TextOverflow.Visible,
                    textAlign = TextAlign.Center
                )
            }
        }
    }


}

@Composable
fun userPlaceHolderView(user: Users, age: String) {
    val photoBitmap = remember(user.photoPath) {
        if (user.photoPath.isNotBlank()) {
            BitmapFactory.decodeFile(user.photoPath)
        } else null
    }

    val imageBitmap = photoBitmap?.asImageBitmap()
        ?: if (user.sex) ImageBitmap.imageResource(R.drawable.placeholder_man) else ImageBitmap.imageResource(
            R.drawable.placeholder_woman
        )
    Column(
        modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "User ава",
            modifier = Modifier
                .clip(RoundedCornerShape(250.dp))
                .size(140.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${user.name}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            modifier = Modifier,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${age}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center
        )
    }
}