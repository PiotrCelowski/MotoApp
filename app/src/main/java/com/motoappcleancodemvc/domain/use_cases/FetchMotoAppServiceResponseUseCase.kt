package com.motoappcleancodemvc.domain.use_cases

import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.MotoAppEntityFactory
import com.motoappcleancodemvc.domain.entities.NavigationInputEntity
import com.motoappcleancodemvc.domain.entities.RouteGeometryEntity
import com.motoappcleancodemvc.domain.interfaces.MotoAppResponseAdapter
import com.motoappcleancodemvc.domain.interfaces.MotoAppRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FetchMotoAppServiceResponseUseCase @Inject constructor(
        private val mMotoAppRepository: MotoAppRepository,
        private val mMotoAppResponseAdapter: MotoAppResponseAdapter,
        private val mMotoAppEntityFactory: MotoAppEntityFactory,
): BaseObservable<FetchMotoAppServiceResponseUseCase.Listener>() {

    interface Listener {
        fun onNavigationInputFetchSuccess(mNavigationInputEntity: NavigationInputEntity, mRouteGeometryEntity: RouteGeometryEntity)
        fun onNavigationInputFetchFailed()
    }

    private lateinit var mMotoAppServiceResponse: String

    fun fetchMotoAppServiceResponseAndNotify(mMotoAppServiceUrl: String) {
        mMotoAppServiceResponse = mMotoAppRepository.fetchMotoAppServiceRoute(mMotoAppServiceUrl)
        notifySuccess(mMotoAppServiceResponse)
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onNavigationInputFetchFailed()
        }
    }

    private fun notifySuccess(mMotoAppServiceResponse: String) {
        val mMotoAppServiceResponseObject = mMotoAppResponseAdapter.convertJsonToRouteGeometry(mMotoAppServiceResponse)
        val mRouteGeometryEntity = mMotoAppEntityFactory.getRouteGeometryEntity(mMotoAppServiceResponseObject!!.routes[0].geometry)
        val mNavigationInputEntity = mMotoAppEntityFactory.getNavigationInputEntity(mMotoAppServiceResponse)

        for (listener in getListeners()) {
            listener.onNavigationInputFetchSuccess(mNavigationInputEntity, mRouteGeometryEntity)
        }
    }

}