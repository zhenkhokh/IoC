package com.example.ioc.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.ioc.model.MainModel

interface IMainVM {
    fun onClickUpProp()
    fun onClickDownProp()
    fun onClickUpPair()
    fun onClickDownPair()
    fun onClickSend()
    fun onClickSync()
    fun model() : MainModel
    fun onFocusLost()
    fun onResume()
    var visibleDownModel: ObservableInt
    fun onClickFmt4()
    fun onClickFmt5()
    fun onClickFpga()
}
