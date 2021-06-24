package com.motoappcleancodemvc.application.services.response_adapter

import com.motoappcleancodemvc.domain.interfaces.MotoAppResponseAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class MotoAppResponseAdapterImpl @Inject constructor(): MotoAppResponseAdapter {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter: JsonAdapter<MotoAppServiceResponseSchema> = moshi.adapter(
        MotoAppServiceResponseSchema::class.java)

    override fun convertJsonToRouteGeometry(mJson: String): MotoAppServiceResponseSchema? {
        return jsonAdapter.fromJson(mJson)
    }

}