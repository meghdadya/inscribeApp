package com.example.note_presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_ui.DarkGray
import com.example.core_ui.LightGray
import com.example.note_presentation.utils.ReminderDateTimeModel
import com.example.note_presentation.utils.formatReminderDateTime
import com.example.note_presentation.utils.formatToLocalDateTime
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DateTimeDialog(
    open: Boolean,
    modifier: Modifier = Modifier,
    onDateTimeUpdated: (LocalDateTime) -> Unit,
    onDismissCallback: () -> Unit,
) {
    if (open) {
        ModalBottomSheet(onDismissRequest = onDismissCallback, containerColor = LightGray) {
            Surface(
                color = LightGray,
                modifier = modifier.padding(bottom = 24.dp),
                tonalElevation = 6.dp
            ) {
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    maxItemsInEachRow = 1
                ) {
                    ReminderDateTimeModel.entries.forEach {
                        if (it != ReminderDateTimeModel.PICK_A_DATE) {
                            TimeRow(
                                timeText = it.formatToLocalDateTime().formatReminderDateTime()
                                    .split(",").first(),
                                time = it.formatToLocalDateTime().formatReminderDateTime()
                                    .split(",").last()
                            ) {
                                onDateTimeUpdated(it.formatToLocalDateTime())
                            }
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(1.dp),
                                color = DarkGray.copy(alpha = 0.1f)
                            )
                        } else {
                            TimePickRow {

                            }
                        }
                    }
                }
            }
        }
    }

}
