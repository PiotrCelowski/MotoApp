package com.motoappcleancodemvc.domain.entities

import javax.inject.Inject

class MotoAppEntityFactory @Inject constructor() {

    fun getMotoAppRequestEntity(mDirection: String, mDistance: Int): MotoAppRequestEntity {
        return MotoAppRequestEntity(mDirection, mDistance)
    }

    fun getNavigationInputEntity(mInstructions: String): NavigationInputEntity {
        return NavigationInputEntity(mInstructions)
    }

    fun getRouteGeometryEntity(mGeometry: String): RouteGeometryEntity {
        return RouteGeometryEntity(mGeometry)
    }

    fun getUserEntity(mLogin: String, id: Int): UserEntity {
        return UserEntity(mLogin, id)
    }
}