package com.example.note_presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.R
import com.example.core_ui.LocalSpacing

@Composable
fun TimeRow(
    timeText: String,
    time: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.clock),
                contentDescription = null,
                tint = Color.Black
            )
            Spacer( modifier = Modifier.width(LocalSpacing.current.spaceSmall))
            Text(
                text = timeText,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ),
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
            )

        }

        Text(
            text = time,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            ),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black,
        )

    }
}