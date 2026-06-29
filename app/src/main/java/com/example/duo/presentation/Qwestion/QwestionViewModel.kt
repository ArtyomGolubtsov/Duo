package com.example.duo.presentation.Qwestion

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.graphics.BitmapCompat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import java.io.FileOutputStream
import kotlin.math.min

data class Users(
    val name: String,
    val sex: Boolean, // true - мужчина, false - женщина
    val dateOfBitrh: String,
    val photoPath: String
)

data class DateOfLove(
    var dateOfLove: String,
)

data class QwestionScreenState(
    val user1: Users = Users("", true, "", ""),
    val user2: Users = Users("", false, "", ""),
    val errorMessage: String = ""
)

class QwestionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QwestionScreenState())
    val uiState: StateFlow<QwestionScreenState> = _uiState.asStateFlow()

    // ======== Методы для изменения первого пользователя ========
    fun onUser1NameChange(name: String) {
        _uiState.update { currentState ->
            val newUser = currentState.user1.copy(name = name)
            val newState = currentState.copy(user1 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser1SexChange(isMale: Boolean) {
        _uiState.update { currentState ->
            val newUser = currentState.user1.copy(sex = isMale)
            val newState = currentState.copy(user1 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser1DateChange(date: String) {
        _uiState.update { currentState ->
            val newUser = currentState.user1.copy(dateOfBitrh = date)
            val newState = currentState.copy(user1 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser1PhotoChange(path: String){
        _uiState.update { currentState->
            val newUser = currentState.user1.copy(photoPath = path)
            val newState = currentState.copy(user1 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    // ======== Методы для изменения второго пользователя ========
    fun onUser2NameChange(name: String) {
        _uiState.update { currentState ->
            val newUser = currentState.user2.copy(name = name)
            val newState = currentState.copy(user2 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser2SexChange(isMale: Boolean) {
        _uiState.update { currentState ->
            val newUser = currentState.user2.copy(sex = isMale)
            val newState = currentState.copy(user2 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser2DateChange(date: String) {
        _uiState.update { currentState ->
            val newUser = currentState.user2.copy(dateOfBitrh = date)
            val newState = currentState.copy(user2 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    fun onUser2PhotoChange(path: String){
        _uiState.update { currentState->
            val newUser = currentState.user2.copy(photoPath = path)
            val newState = currentState.copy(user2 = newUser)
            newState.copy(errorMessage = validate(newState))
        }
    }

    //========= Функция для сохранения фото =====
    fun saveImageToInternalStorage(context: Context, sourceUri: Uri, maxSize: Int = 512): String{
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        context.contentResolver.openInputStream(sourceUri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        }

        options.inSampleSize = calculateInSampleSize(
            options.outWidth, options.outHeight, maxSize, maxSize
        )
        options.inJustDecodeBounds = false

        val originalBitmap = context.contentResolver.openInputStream(sourceUri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        } ?: return ""

        val cropSize = min(originalBitmap.width, originalBitmap.height)
        val cropX = (originalBitmap.width - cropSize) / 2
        val cropY = (originalBitmap.height - cropSize) / 2
        val croppedBitmap = Bitmap.createBitmap(originalBitmap, cropX, cropY, cropSize, cropSize)

        if (croppedBitmap != originalBitmap) {
            originalBitmap.recycle()
        }

        val scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, maxSize, maxSize, true)
        if (scaledBitmap != croppedBitmap) {
            croppedBitmap.recycle()
        }

        val fileName = "user_photo_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        FileOutputStream(file).use { out ->
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
        }
        scaledBitmap.recycle()

        return file.absolutePath
    }

    fun calculateInSampleSize(width: Int, height: Int, reqWidth: Int, reqHeight: Int): Int {
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    // ======== Приватный метод проверки ========
    private fun validate(state: QwestionScreenState): String {
        val emptyDate = "Выбрать дату"
        val user1 = state.user1
        val user2 = state.user2

        if (user1.name.isBlank() || user1.dateOfBitrh == emptyDate || user1.dateOfBitrh.isBlank()) {
            return "Введите данные для первого пользователя!"
        }
        if (user2.name.isBlank() || user2.dateOfBitrh == emptyDate || user2.dateOfBitrh.isBlank()) {
            return "Введите данные для второго пользователя!"
        }
        if (user1.sex == user2.sex) {
            return "Пользователи должны быть разных полов!"
        }
        return "1" // успех
    }

    // Вспомогательное свойство, чтобы UI не сравнивал строки
    val isDataValid: Boolean
        get() = uiState.value.errorMessage == "1"
}