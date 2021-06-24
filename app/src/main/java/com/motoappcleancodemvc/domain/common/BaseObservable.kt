package com.motoappcleancodemvc.domain.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

open class BaseObservable<LISTENER_CLASS> {

    // thread-safe set of listeners
    private val mListeners: MutableSet<LISTENER_CLASS> = Collections.newSetFromMap(
        ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
    )


    fun registerListener(listener: LISTENER_CLASS) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        mListeners.remove(listener)
    }

    protected fun getListeners(): Set<LISTENER_CLASS> {
        return Collections.unmodifiableSet(mListeners)
    }
}