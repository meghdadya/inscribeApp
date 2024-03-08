package com.example.note_presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.Blue
import com.example.core_ui.LocalSpacing
import com.example.core.R

@Composable
fun AlarmLabel(
    text: String
) {
    val spacing = LocalSpacing.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(
                color = Blue,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(spacing.spaceSmall)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.timer),
            contentDescription = "Alarm Icon"
        )
        Spacer(modifier = Modifier.height(spacing.spaceSuperExtraSmall))
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight(500),
            lineHeight = 15.sp,
            color = Color.White,
        )
    }
}

@Composable
@Preview
fun PreviewAlarmLabel() {
    AlarmLabel(text = "Tomorrow, 18:00")
}