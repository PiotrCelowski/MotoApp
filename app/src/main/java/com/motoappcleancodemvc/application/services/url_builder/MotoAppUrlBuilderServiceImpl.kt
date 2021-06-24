package com.motoappcleancodemvc.application.services.url_builder

import com.motoappcleancodemvc.domain.entities.MotoAppRequestEntity
import com.motoappcleancodemvc.domain.interfaces.MotoAppUrlBuilderService
import javax.inject.Inject

class MotoAppUrlBuilderServiceImpl @Inject constructor(): MotoAppUrlBuilderService {
    override fun buildUrl(mMotoAppRequestEntity: MotoAppRequestEntity): String {
        return "exampleUrl"
    }
}