package com.motoappcleancodemvc.application.services.moto_app_service

import android.content.Context
import com.motoappcleancodemvc.domain.interfaces.MotoAppRepository
import dagger.hilt.android.qualifiers.ActivityContext
import java.io.BufferedReader


class MotoAppRepositoryImpl (@ActivityContext private val context: Context): MotoAppRepository {

    override fun fetchMotoAppServiceRoute(mMotoAppServiceUrl: String): String {
        //only read file, dont use the url yet
        return readAsset("testInputs/exampleResponse")
    }

    //Helper function
    fun readAsset(fileName: String): String = context.assets.open(fileName).bufferedReader().use(BufferedReader::readText)
}