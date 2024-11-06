package com.example.ioc.model

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class MainModel(val view: Context)  {
        private val _focus4Fmt: ObservableBoolean = ObservableBoolean(false)
        private val _focus5Fmt: ObservableBoolean = ObservableBoolean(false)
        private val _focusFpga: ObservableBoolean = ObservableBoolean(false)
        private var _prop: ObservableField<String> = ObservableField("")
        private var _value: ObservableField<String> = ObservableField("")
        private var _message : ObservableField<String> = ObservableField("")
        private var _onKeyListenerProp : ObservableField<View.OnKeyListener>
        private var _onKeyListenerVal : ObservableField<View.OnKeyListener>

    val prop: ObservableField<String>
//    @get:Bindable
        get() = _prop

//        set(value) {
//            _prop = value}

    val value: ObservableField<String>
        get()  = _value
    val message: ObservableField<String>
        get() = _message
    var onKeyListenerProp : ObservableField<View.OnKeyListener>
        get() = _onKeyListenerProp
        set(v) {_onKeyListenerProp = v}
    var onKeyListenerVal : ObservableField<View.OnKeyListener>
        get() = _onKeyListenerVal
        set(v) {_onKeyListenerVal = v}
    val focus4Fmt: ObservableBoolean
        get() = _focus4Fmt
    val focus5Fmt: ObservableBoolean
        get() = _focus5Fmt
    val focusFpga: ObservableBoolean
        get() = _focusFpga

    init {
        _onKeyListenerProp = ObservableField()
        _onKeyListenerVal = ObservableField()
    }
}