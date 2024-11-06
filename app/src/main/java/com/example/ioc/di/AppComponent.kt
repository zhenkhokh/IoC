package com.example.ioc.di

import com.example.ioc.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class
    ,AppModule::class
    ,MainBinding::class
    ,MainFragmentBinding::class
])
interface AppComponent : AndroidInjector<Application?> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun appModule(appModule: AppModule?): Builder?
        fun build(): AppComponent?
    }
}