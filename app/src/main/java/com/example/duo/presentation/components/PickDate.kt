package com.example.duo.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalendarPickDate(closeSelection: UseCaseState.() -> Unit,  selectedDate: MutableState<LocalDate?> ) {
    CalendarDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            displayCalendarWeeks = true,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Date(   // ← одиночный выбор
            selectedDate = selectedDate.value,
            onSelectDate = { newDate ->          // ← правильное имя параметра
                selectedDate.value = newDate
            }
        ),
    )
}

@Preview
@Composable
fun PreviewCalendarPickDate(){
    val dateState = remember { mutableStateOf<LocalDate?>(null) }
    var showDialog by remember { mutableStateOf(true) }
    CalendarPickDate(
        closeSelection = { showDialog = false },
        selectedDate = dateState
    )
}