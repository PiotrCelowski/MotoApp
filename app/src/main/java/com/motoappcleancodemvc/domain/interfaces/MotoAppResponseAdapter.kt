package com.motoappcleancodemvc.domain.interfaces

import com.motoappcleancodemvc.application.services.response_adapter.MotoAppServiceResponseSchema


interface MotoAppResponseAdapter {
    fun convertJsonToRouteGeometry(mJson: String): MotoAppServiceResponseSchema?
}