package com.example.ioc.di

import com.example.ioc.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {
    @Binds
    abstract fun bindActivity(activity: MainActivity? ): MainActivity?
}