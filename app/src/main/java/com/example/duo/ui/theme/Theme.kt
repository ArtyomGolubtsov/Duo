package com.example.duo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    // Главный цвет приложения: кнопки, активные элементы, сердце Duo
    primary = Color(0xFFF4A6BC),

    // Цвет вторичных элементов: спокойные акценты, иконки, вкладки
    secondary = Color(0xFFC4B5F5),

    // Дополнительный акцент: важные даты, годовщины, премиум
    tertiary = Color(0xFFE8C98A),


    // Фон всего приложения
    background = Color(0xFF17131C),

    // Цвет карточек, диалогов, поверхностей
    surface = Color(0xFF241D2B),

    // Поверхности второго уровня (например карточки внутри карточек)
    surfaceVariant = Color(0xFF30263A),


    // Цвет текста на primary кнопках
    onPrimary = Color(0xFF3A1D28),

    // Цвет текста на secondary элементах
    onSecondary = Color(0xFF241B35),

    // Цвет текста на tertiary элементах
    onTertiary = Color(0xFF332817),


    // Основной текст
    onBackground = Color(0xFFFFF7FA),

    // Текст внутри карточек
    onSurface = Color(0xFFFFF7FA),


    // Второстепенный текст
    onSurfaceVariant = Color(0xFFD1C5D8),


    // Ошибки
    error = Color(0xFFFF8A8A),

    // Текст ошибок
    onError = Color(0xFF3B1115)
)


private val LightColorScheme = lightColorScheme(

    // Главный цвет приложения: кнопки, сердечки, основные действия
    primary = Color(0xFFF29BB2),

    // Вторичный цвет: воспоминания, спокойные элементы
    secondary = Color(0xFFB8A5E8),

    // Акцент: годовщины, особые события
    tertiary = Color(0xFFE8C98A),


    // Основной фон приложения
    background = Color(0xFFFFF8F5),

    // Карточки, окна, элементы поверх фона
    surface = Color(0xFFFFFFFF),

    // Вторичные поверхности
    surfaceVariant = Color(0xFFFFD9E2),


    // Текст на главных кнопках
    onPrimary = Color(0xFFFFFFFF),

    // Текст на вторичных кнопках
    onSecondary = Color(0xFFFFFFFF),

    // Текст на золотых элементах
    onTertiary = Color(0xFF4B3B55),


    // Главный текст приложения
    onBackground = Color(0xFF4B3B55),

    // Текст внутри карточек
    onSurface = Color(0xFF4B3B55),


    // Второстепенный текст
    onSurfaceVariant = Color(0xFF7D6F86),


    // Ошибки
    error = Color(0xFFE57373),

    // Текст ошибок
    onError = Color(0xFFFFFFFF)
)

@Composable
fun DuoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}