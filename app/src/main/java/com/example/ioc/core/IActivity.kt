package com.example.ioc.core

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface IActivity {

    fun removeFragment(fragment: Fragment)
    val manager: FragmentManager?
    val activity: Activity
    fun switchToFragment(fragmentId: Int, fragment: Fragment)
    fun showError(throwable: Throwable)
    fun context(): Context
}
