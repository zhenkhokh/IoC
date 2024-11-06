package com.example.ioc.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class AbstractActivity <B: ViewDataBinding?>: AppCompatActivity() {
    var binding: B? = null
        protected abstract val layoutId: Int
    protected abstract fun initComponent()
    protected abstract fun onInitBinding(binding: B?)
    protected abstract fun onResumeBinding(binding: B?)
    protected abstract fun onDestroyBinding(binding: B?)
    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
//        supportActionBar!!.setIcon(R.mipmap.ic_launcher)
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        error = ErrorControl(this)
        binding = DataBindingUtil.inflate<B>(LayoutInflater.from(this)!!, layoutId,null,false)

        setContentView(binding!!.getRoot())
    }

    override fun onStart() {
        initComponent()
        super.onStart()
        onInitBinding(binding)
    }

    override fun onResume() {
        super.onResume()
        onResumeBinding(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyBinding(binding)
        binding!!.unbind()
    }
}