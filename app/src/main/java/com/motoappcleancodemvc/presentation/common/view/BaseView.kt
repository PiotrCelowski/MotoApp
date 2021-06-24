package com.motoappcleancodemvc.presentation.common.view

open class BaseView<LISTENER_TYPE>() {
    protected var mListeners: MutableList<LISTENER_TYPE> = mutableListOf<LISTENER_TYPE>()

    fun registerListener(listener: LISTENER_TYPE) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        mListeners.remove(listener)
    }
}