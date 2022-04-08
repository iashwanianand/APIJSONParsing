package com.example.apijsonparsing.base

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseAndroidViewModel> : AppCompatActivity() {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    private var isLoading = ObservableField(false)
    var badgeCount: TextView? = null

    /**
     * Override for set binding variable
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */

    abstract fun getViewModel(): V

    fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setObserversOnBaseActivity()
    }

    /**
     * Set observers
     */
    private fun setObserversOnBaseActivity() {

    }

    /**
     * Method to perform data binding on layout for activity
     */
    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = when (this.mViewModel) {
            null -> getViewModel()
            else -> mViewModel
        }
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
        mViewDataBinding!!.lifecycleOwner = this
    }

    public fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager()
        } else {
            return false
        }
    }
}