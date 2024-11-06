package com.example.ioc.di

import com.example.ioc.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBinding {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainBindingModule::class])
    abstract fun mainFragment(): MainFragment?
}