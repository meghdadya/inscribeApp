package com.example.note_presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.*

@Composable
fun TagText(
    text: String
) {
    val spacing = LocalSpacing.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(
                color = LightBlue.copy(alpha = 0.25f),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(
                top = spacing.spaceSmall,
                bottom = spacing.spaceSmall,
                end = spacing.spaceMedium,
                start = spacing.spaceMedium
            )
    ) {
        Text(
            text = "Work",
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            lineHeight = 18.sp,
            color = Blue,
        )
    }
}
