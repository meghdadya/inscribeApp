package com.example.note_presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.DarkGray
import com.example.core_ui.LocalSpacing
import com.example.core_ui.MediumGray
import com.example.note_presentation.note.NoteUiModel
import com.example.note_presentation.utils.formatReminderDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridNotesCard(
    noteModel: NoteUiModel,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        border = BorderStroke(width = 1.dp, MediumGray),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = noteModel.title,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = noteModel.content,
                    fontSize = 12.sp,
                    maxLines = 5,
                    fontWeight = FontWeight(400),
                    overflow = TextOverflow.Ellipsis,
                    color = DarkGray
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {
                    noteModel.alarm?.let {
                        AlarmLabel(text = it.formatReminderDateTime())
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    TagText(text = noteModel.label)
                }
            }
        }
    }
}