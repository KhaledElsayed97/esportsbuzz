package com.kholiodev.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kholiodev.core.R

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_light,FontWeight.Light),
    Font(R.font.poppins_regular,FontWeight.Normal),
    Font(R.font.poppins_medium,FontWeight.Medium),
    Font(R.font.poppins_bold,FontWeight.Bold)
)

val PoppinsTypography = Typography(
    displayLarge = Typography().displayLarge.copy(fontFamily = poppinsFontFamily),
    headlineLarge = Typography().headlineLarge.copy(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = Typography().bodyMedium.copy(
        fontFamily = poppinsFontFamily
    ),
    titleMedium = Typography().bodyMedium.copy(
        fontFamily = poppinsFontFamily
    )
)