package com.example.services.di

import com.example.services.alarm.AlarmScheduler
import com.example.services.alarm.AlarmSchedulerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AlarmModule {
    @Binds
    abstract fun bindAlarm(
        alarmSchedulerImpl: AlarmSchedulerImpl,
    ): AlarmScheduler

}