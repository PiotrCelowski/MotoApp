package com.motoappcleancodemvc.domain.use_cases

import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.MotoAppRequestEntity
import com.motoappcleancodemvc.domain.interfaces.MotoAppUrlBuilderService
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ConfigureMotoAppServiceRequestUseCase @Inject constructor(private val mMotoAppUrlBuilderService: MotoAppUrlBuilderService) : BaseObservable<ConfigureMotoAppServiceRequestUseCase.Listener>() {

    interface Listener {
        fun onMotoAppServiceRequestCreated(mRequestUrl: String)
    }

    fun createMotoAppServiceRequestAndNotify(mDirection: String, mDistance: Int) {
        val mMotoAppRequestEntity = MotoAppRequestEntity(mDirection, mDistance)
        notifyListeners(mMotoAppUrlBuilderService.buildUrl(mMotoAppRequestEntity))
    }

    private fun notifyListeners(mUrl: String) {
        for (listener in getListeners()) {
            listener.onMotoAppServiceRequestCreated(mUrl)
        }
    }


}