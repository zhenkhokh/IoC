package com.example.ioc.ui.main

import com.example.ioc.R
import com.example.ioc.core.AbstractFragment
import com.example.ioc.databinding.MainFragmentBinding
import com.example.ioc.vm.IMainVM
import com.example.ioc.vm.MainViewModel
import javax.inject.Inject

class MainFragment : AbstractFragment<MainFragmentBinding>() {

//    companion object {
//        fun newInstance() = MainFragment()
//    }
    @Inject
    lateinit var viewModel: IMainVM

    override val layoutId: Int
        get() = R.layout.main_fragment

    override fun initComponent() {
//        TODO("Not yet implemented")
    }

    override fun onInitBinding(binding: MainFragmentBinding) {
        binding.vm = viewModel
        binding.model = viewModel.model()
    }

    override fun onResumeBinding(binding: MainFragmentBinding) {
        viewModel.onResume()
    }

    override fun onDestroyBinding(binding: MainFragmentBinding) {
//        TODO("Not yet implemented")
    }

}