package com.example.domain.models

import java.time.LocalDateTime


data class NoteDomainModel(
    val title: String,
    val content: String,
    val alarm: LocalDateTime?,
    var isReminded: Boolean = false,
    val id: Int?=null
) : DomainModel