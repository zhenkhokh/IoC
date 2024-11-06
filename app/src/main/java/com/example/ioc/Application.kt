package com.example.ioc

import com.example.ioc.di.AppComponent
import com.example.ioc.di.AppModule
import dagger.android.support.DaggerApplication
import com.example.ioc.di.DaggerAppComponent

class Application : DaggerApplication() {
    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder()!!
                .application(this)!!
                .appModule(AppModule(this))!!
                .build()
    }
}