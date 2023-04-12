package com.example.daggerapp.screens.commons.viewsmvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE> (private val layoutInflater: LayoutInflater,
                                       private val parent: ViewGroup?,
                                       @LayoutRes private val layoutId:Int) {

    protected val listeners = HashSet<LISTENER_TYPE>()
    val rootview: View = layoutInflater.inflate(layoutId, parent, false)
    protected val context: Context get() = rootview.context


    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }


    protected fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootview.findViewById<T>(id)
    }

}