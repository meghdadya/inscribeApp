package com.example.data.utils


import java.time.LocalDateTime
import java.time.temporal.ChronoUnit



fun LocalDateTime.triggerDateTime(): Long {
    val currentDateTime = LocalDateTime.now()
    return currentDateTime.until(this, ChronoUnit.MILLIS)
}
