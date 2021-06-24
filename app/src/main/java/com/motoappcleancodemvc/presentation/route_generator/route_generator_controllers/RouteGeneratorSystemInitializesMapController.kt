package com.motoappcleancodemvc.presentation.route_generator.route_generator_controllers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.motoappcleancodemvc.application.services.location_service.LocationServiceConnector
import com.motoappcleancodemvc.application.services.location_service.LocationServiceConnectorImpl
import com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.views.RouteGeneratorSystemInitializesMapView

class RouteGeneratorSystemInitializesMapController(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
    context: Context,
    private val mLocationServiceConnectorImpl: LocationServiceConnector) {

    private var mRouteGeneratorRouteGeneratorSystemInitializesMapView: RouteGeneratorSystemInitializesMapView =
        RouteGeneratorSystemInitializesMapView(inflater, container, savedInstanceState, context)

    fun initializeMapTiles(): View {
        mRouteGeneratorRouteGeneratorSystemInitializesMapView.initializeMap()
        return mRouteGeneratorRouteGeneratorSystemInitializesMapView.getRootView()
    }

    fun bindLocationService() {
        mLocationServiceConnectorImpl.bindLocationService()
    }

    fun startLocationUpdates() {
        mLocationServiceConnectorImpl.startLocationUpdates()
    }

    fun enableMapLocation() {
        mRouteGeneratorRouteGeneratorSystemInitializesMapView.enableLocation()
    }

    fun stopLocationUpdates() {
        mLocationServiceConnectorImpl.stopLocationUpdates()
    }
}