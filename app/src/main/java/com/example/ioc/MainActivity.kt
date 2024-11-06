package com.example.ioc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ioc.core.AbstractActivity
import com.example.ioc.ui.main.MainFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import com.example.ioc.databinding.MainActivityBinding
import dagger.android.AndroidInjector

//import com.example.ioc.R
//ViewDataBinding
class MainActivity  : AbstractActivity<MainActivityBinding>(), HasAndroidInjector  {
    val manager: FragmentManager = supportFragmentManager

    override val layoutId  = R.layout.main_activity
    override fun initComponent() {
                AndroidInjection.inject(this)
    }
    override  fun onInitBinding(binding: MainActivityBinding?) {
        switchToFragment(R.id.mainFragment, MainFragment())
    }
    override  fun onResumeBinding(binding: MainActivityBinding?) {
        //TODO
    }
    override  fun onDestroyBinding(binding: MainActivityBinding?) {

    }


//    override fun showError(throwable: Throwable) {
//        error!!.showError(throwable, Consumer{ a: Boolean? -> })
//    }
//
     fun switchToFragment(fragmentId: Int, fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(fragmentId, fragment)
        transaction.commit()
    }

     fun removeFragment(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }
    companion object {
        lateinit var _androidInjector: DispatchingAndroidInjector<Any>
    }
    @Inject
    lateinit var androidInjecto: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        _androidInjector = if (::androidInjecto.isInitialized ) androidInjecto
        else _androidInjector
        return _androidInjector
    }


//
//    override val manager: FragmentManager = supportFragmentManager
//
//    override val activity
//        get() = this
//
//    override fun context() = activity

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//    }
}