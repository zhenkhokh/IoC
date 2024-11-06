package com.example.ioc.di

import android.content.Context
import com.example.ioc.Application
import com.example.ioc.model.MainModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(app: Application) {
    private var context: Context;
    init {
        context = app.applicationContext
    }
    @Singleton
    @Provides
    fun provideView(): Context {
        return context;
    }
    @Provides
    fun mainModel(view: Context): MainModel {
        return MainModel(view)
    }
}