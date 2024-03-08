package com.example.domain.models

data class ListDomainModel<T : DomainModel>(
    val item: List<T>
) : DomainModel
