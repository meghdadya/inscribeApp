package com.example.domain.usecases


import com.example.domain.models.DomainModel

abstract class BaseUseCase<Param, out R : DomainModel> {
    abstract suspend fun execute(param: Param): R
}