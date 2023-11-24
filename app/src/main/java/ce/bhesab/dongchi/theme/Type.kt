package ce.bhesab.dongchi.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ce.bhesab.dongchi.R

val vazirFontFamily = FontFamily(
    Font(R.font.vazir_variable),
    Font(R.font.vazir_regular, FontWeight.Normal),
    Font(R.font.vazir_bold, FontWeight.Bold),
    Font(R.font.vazir_medium, FontWeight.Medium),
    Font(R.font.vazir_thin, FontWeight.Light)
)

// Set of Material typography styles to start with
val Typography = Typography(
    // display
    displayLarge = TextStyle(
        fontFamily = vazirFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(fontFamily = vazirFontFamily),
    displaySmall = TextStyle(fontFamily = vazirFontFamily),
    // headline
    headlineLarge = TextStyle(fontFamily = vazirFontFamily),
    headlineMedium = TextStyle(fontFamily = vazirFontFamily),
    headlineSmall = TextStyle(fontFamily = vazirFontFamily),
    // title
    titleLarge = TextStyle(fontFamily = vazirFontFamily),
    titleMedium = TextStyle(fontFamily = vazirFontFamily),
    titleSmall = TextStyle(fontFamily = vazirFontFamily),
    // body
    bodyLarge = TextStyle(
        fontFamily = vazirFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(fontFamily = vazirFontFamily),
    bodySmall = TextStyle(fontFamily = vazirFontFamily),
    // label
    labelLarge = TextStyle(fontFamily = vazirFontFamily),
    labelMedium = TextStyle(fontFamily = vazirFontFamily),
    labelSmall = TextStyle(fontFamily = vazirFontFamily)
)