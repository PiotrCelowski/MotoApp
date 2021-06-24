package com.motoappcleancodemvc.domain.interfaces

interface MotoAppRepository {
    fun fetchMotoAppServiceRoute(mMotoAppServiceUrl : String): String
}