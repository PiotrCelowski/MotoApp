package com.motoappcleancodemvc.domain.interfaces

import com.motoappcleancodemvc.domain.entities.MotoAppRequestEntity

interface MotoAppUrlBuilderService {
    fun buildUrl(mMotoAppRequestEntity: MotoAppRequestEntity): String
}