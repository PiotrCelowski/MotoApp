package com.motoappcleancodemvc.domain.use_cases

import android.location.Location
import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.UserEntity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

class UpdateUserLocationUseCase @Inject constructor() : BaseObservable<UpdateUserLocationUseCase.Listener>() {

    interface Listener {
        fun onUserCoordinatesChanged(mUser: UserEntity)
    }

    fun setUserCoordinatesAndNotify(mUser: UserEntity, mUserLocation: Location) {
        mUser.userLocation = mUserLocation
        notifyListeners(mUser)
    }

    fun notifyListeners(mUser: UserEntity) {
        for(listener in getListeners()) {
            listener.onUserCoordinatesChanged(mUser)
        }
    }
}