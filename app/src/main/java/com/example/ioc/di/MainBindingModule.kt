package com.example.ioc.di

import android.content.Context
import com.example.ioc.MainActivity
import com.example.ioc.model.MainModel
import com.example.ioc.ui.main.MainFragment
import com.example.ioc.vm.IMainVM
import com.example.ioc.vm.MainViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [MainModule::class])
class MainBindingModule {
    @Provides
    fun mainVM(view: MainFragment, model: MainModel) : IMainVM {
        return MainViewModel(view, model)
    }
}
