package com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.motoappcleancodemvc.R
import com.motoappcleancodemvc.application.services.location_service.LocationServiceConnector
import com.motoappcleancodemvc.presentation.permissions.MyPermission
import com.motoappcleancodemvc.presentation.permissions.PermissionsHelper
import com.motoappcleancodemvc.presentation.route_generator.route_generator_controllers.RouteGeneratorSystemInitializesMapController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RouteGeneratorSystemInitializesMapFragment : Fragment(), OnMapReadyCallback, PermissionsHelper.Listener, ActivityCompat.OnRequestPermissionsResultCallback {
    @Inject lateinit var mLocationServiceConnectorImpl: LocationServiceConnector
    @Inject lateinit var mPermissionsHelper: PermissionsHelper
    private val mPermissions = MyPermission.values()
    private val REQUEST_CODE = 1

    private lateinit var mRouteGeneratorSystemInitializesMapController: RouteGeneratorSystemInitializesMapController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
        mPermissionsHelper.registerObserver(this)
        mRouteGeneratorSystemInitializesMapController = RouteGeneratorSystemInitializesMapController(
            inflater,
            container,
            savedInstanceState,
            requireContext(),
            mLocationServiceConnectorImpl
        )
        val rootView = mRouteGeneratorSystemInitializesMapController.initializeMapTiles()
        mRouteGeneratorSystemInitializesMapController.enableMapLocation()
        return rootView
    }

    override fun onStart() {
        if (!mPermissionsHelper.hasAllPermissions(mPermissions)) {
            mPermissionsHelper.requestAllPermissions(mPermissions, REQUEST_CODE)
        } else {
            mRouteGeneratorSystemInitializesMapController.enableMapLocation()
            mRouteGeneratorSystemInitializesMapController.bindLocationService()
            mRouteGeneratorSystemInitializesMapController.startLocationUpdates()
        }
        super.onStart()
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
//        TODO("Not yet implemented")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        result: PermissionsHelper.PermissionsResult?
    ) {
        if (!mPermissionsHelper.hasAllPermissions(mPermissions)) {
            mPermissionsHelper.requestAllPermissions(mPermissions, REQUEST_CODE)
        } else {
            mRouteGeneratorSystemInitializesMapController.enableMapLocation()
            mRouteGeneratorSystemInitializesMapController.bindLocationService()
            mRouteGeneratorSystemInitializesMapController.startLocationUpdates()
        }
    }

    override fun onPermissionsRequestCancelled(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        mPermissionsHelper.unregisterObserver(this)
        super.onStop()
    }

    override fun onDestroy() {
        mRouteGeneratorSystemInitializesMapController.stopLocationUpdates()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // handle the result
        mPermissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
