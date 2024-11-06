package com.example.ioc.di

import com.example.ioc.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class MainBinding {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainBindingModule::class])
    abstract fun mainActivity(): MainActivity?
}