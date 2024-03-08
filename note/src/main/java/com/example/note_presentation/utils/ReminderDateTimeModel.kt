package com.example.note_presentation.utils

import java.time.LocalDateTime

enum class ReminderDateTimeModel {
    AFTER_1_HOUR,
    TOMORROW_MORNING_7,
    PICK_A_DATE,
}

fun ReminderDateTimeModel.formatToLocalDateTime(): LocalDateTime {
    return when (this) {
        ReminderDateTimeModel.AFTER_1_HOUR -> LocalDateTime.now().plusHours(1)
        ReminderDateTimeModel.TOMORROW_MORNING_7 -> LocalDateTime.now().withHour(7).withMinute(0).plusDays(1)
        ReminderDateTimeModel.PICK_A_DATE -> LocalDateTime.now()
    }
}
