package com.example.ioc.vm

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import android.view.View.INVISIBLE
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.ObservableInt
import com.example.ioc.R
import com.example.ioc.model.Communnication
import com.example.ioc.model.JsonMap
import com.example.ioc.model.MainModel
import com.example.ioc.model.Pojo
import com.example.ioc.ui.main.MainFragment
import java.lang.StringBuilder


class MainViewModel(val view: MainFragment, val model: MainModel): IMainVM {
    val viewer: Activity = view.activity
    lateinit var  editTextVal: EditText
    lateinit var  editTextProp: EditText
    val communication = Communnication()


    init {
        model.onKeyListenerProp.set(onKeyProp())
        model.onKeyListenerVal.set(onKeyVal())
    }

    val _visibleDownModel = ObservableInt(INVISIBLE)
    override var visibleDownModel: ObservableInt = _visibleDownModel
        get() {
            if (this::map.isInitialized)
                return  ObservableInt(View.VISIBLE)
            return ObservableInt(INVISIBLE)
        }

    override fun onClickFmt4() {
//        model.message.set("Создан компонент с поддержкой 4 формата")
        model.message.set("Supporting 4 format component is created. Sync should be done")
    }

    override fun onClickFmt5() {
//        model.message.set("Создан компонент с поддержкой 5 формата")
        model.message.set("Supporting 5 format component is created. Sync should be done")
    }

    override fun onClickFpga() {
//        model.message.set("Создан компонент с поддержкой Dvb-S2/S2X")
        model.message.set("Supporting Dvb-S2/S2X component is created. Sync should be done")
    }


    fun fclProp() :View.OnFocusChangeListener = View.OnFocusChangeListener {
        v, hasFocus ->
            if (!hasFocus) {
                onFocusLost()
                return@OnFocusChangeListener
            }
            v.apply {
                setFocusable(true)
                setFocusableInTouchMode(true)
                requestFocus()
            }
            onFocus()
    }

    fun fclVal() :View.OnFocusChangeListener = View.OnFocusChangeListener {
        v, hasFocus ->
            if (!hasFocus) {
                onFocusLost()
                return@OnFocusChangeListener
            }
            v.apply {
                setFocusable(true)
                setFocusableInTouchMode(true)
                requestFocus()
            }
            onFocus()
    }

    private fun onFocus() {
       model.message.set( StringBuilder()
            .append(model.prop.get()).append(":")
            .append(model.value.get()).toString())
    }

    fun onKeyProp(): View.OnKeyListener = View.OnKeyListener {
        view: View, i: Int, keyEvent: KeyEvent ->
        model.message.set( StringBuilder()
            .append("To modify property, press up/down pair ")
            .append(model.prop.get()).append(":")
            .append(model.value.get()).toString())
        true
    }

    fun onKeyVal(): View.OnKeyListener = View.OnKeyListener {
        view: View, i: Int, keyEvent: KeyEvent ->
        model.message.set( StringBuilder()
            .append("To modify value, press up/down pair ")
            .append(model.prop.get().toString()).append(":")
            .append(model.value.get().toString()).toString())
        true
    }
    lateinit var map: Map<String, Pojo>;
    override fun onClickUpProp() {
        if (!this::map.isInitialized)
                return
        if (map.containsKey(communication.key)
            && map.get(communication.key)!!.component.size==0)
            return
        map = communication.upModel()
        model.prop.set(communication.key)
        model.value.set(map.get(communication.key).toString())
        model.message.set("Up model is done")
    }

        override fun onClickDownProp() {
            if (!this::map.isInitialized)
                return
            val key = model.prop.get()
            if (!map.containsKey(key))
                return
            val cudField :Pojo = map.get(key)!!
            //Pojo(model.prop.get()?:"null", model.value.get()?:"null")
            //TODO model
            map = communication.downModel(cudField)
            model.prop.set(communication.key)
            model.value.set(map.get(communication.key).toString())
            model.message.set("Down model is done")
        }

        override fun onClickUpPair() {
            if (communication.json.component.size==0)
                return
            val key = model.prop.get()?:communication.key
//        communication.crudValue(key, model.value.get()?:map.get(key)!!)
            communication.crudValue(key,Pojo.build(model.value.get()))
            val pair = communication.nextPair(key)
            model.prop.set(pair.first)
            model.value.set(pair.second)
            model.message.set("Go to up pair")
        }

        override fun onClickDownPair() {
            if (communication.json.component.size==0)
                return
            val key = model.prop.get()?:communication.key
//        communication.crudValue(key, model.value.get()?:map.get(key)!!)
            communication.crudValue(key,Pojo.build(model.value.get()))
            val pair = communication.prevPair(key)
            model.prop.set(pair.first)
            model.value.set(pair.second)
            model.message.set("Go to down pair")
        }

        override fun onClickSend() {
            if (!this::map.isInitialized)
                return
            val rootName = map.get(communication.key)?.component?.get(0)?.modelName
            val modelName = rootName?:"null"
            model.message.set("\""+modelName+"\":"+map.toString().replace(" ",""))
        }

        override fun onClickSync() {
            var key = Communnication.COMP.NOTHING
            if (model.focus4Fmt.get())
                key =  Communnication.COMP.FMT4
            if (model.focus5Fmt.get())
                key = Communnication.COMP.FMT5
            if (model.focusFpga.get())
                key = Communnication.COMP.FPGA
            map = communication.syncWithComponent(key)

            val rootName = map.get(communication.key)?.component?.get(0)?.modelName
            val modelName = rootName?:""
            if (!modelName.isBlank())
                model.message.set("\""+modelName+"\":"+map.toString().replace(" ",""))
            else
                model.message.set("Model name is empty, navigate to up model")
//                model.message.set("Имя модели пусто, перейдите на другой уровень")
        }

        override fun onFocusLost() {
            Toast.makeText(view.context, "Focus lost", 10)
        }

        override fun onResume() {
            editTextVal = viewer!!.findViewById(R.id.textVal)
            editTextProp = viewer.findViewById(R.id.textProp)
            editTextProp.setOnFocusChangeListener(fclProp())
            editTextVal.setOnFocusChangeListener(fclVal())
        }


    override fun model(): MainModel = model
}



