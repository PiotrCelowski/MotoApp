package com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.motoappcleancodemvc.databinding.FragmentMapboxMapsBinding
import com.motoappcleancodemvc.presentation.common.view.BaseView


class RouteGeneratorSystemInitializesMapView (
        private val layoutInflater: LayoutInflater,
        private val parent: ViewGroup?,
        private val savedInstanceState: Bundle?,
        private val context: Context
): BaseView<RouteGeneratorUserConfiguresRouteView.Listener>() {

    private var mMapView: MapView? = null
    private lateinit var mMapboxMap: MapboxMap
    private val mFragmentMapboxMapsBinding: FragmentMapboxMapsBinding

    init {
        mFragmentMapboxMapsBinding = FragmentMapboxMapsBinding.inflate(layoutInflater, parent, false)
    }

    fun initializeMap() {
        mMapView = mFragmentMapboxMapsBinding.mapView
        mMapView?.onCreate(savedInstanceState)
        mMapView?.getMapAsync { mapboxMap ->
            //Assign mapboxMap to current class fields and
            //change its style and enable localization
            mMapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                enableLocation(it)
            }
        }
    }

    fun enableLocation() {
        if(this::mMapboxMap.isInitialized) {
            enableLocation(mMapboxMap.style!!)
        }
    }

    fun enableLocation(style: Style) {
        //add location layer to the map
        val locationComponentOptions = LocationComponentOptions.builder(context)
                .compassAnimationEnabled(true)
                .pulseEnabled(true)
                .build()

        val locationComponentActivationOptions = LocationComponentActivationOptions
                .builder(context, style)
                .locationComponentOptions(locationComponentOptions)
                .build()

        val locationComponent: LocationComponent = mMapboxMap.locationComponent
        locationComponent.activateLocationComponent(locationComponentActivationOptions)

        //set location component visible on the map
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationComponent.isLocationComponentEnabled = true
        }
    }

    fun getRootView(): View {
        return mFragmentMapboxMapsBinding.root
    }


}