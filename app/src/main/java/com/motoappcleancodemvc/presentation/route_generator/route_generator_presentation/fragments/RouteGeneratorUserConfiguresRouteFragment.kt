package com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.views.RouteGeneratorUserConfiguresRouteView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteGeneratorUserConfiguresRouteFragment : Fragment(), RouteGeneratorUserConfiguresRouteView.Listener {
    private lateinit var mRouteGeneratorUserConfiguresRouteView: RouteGeneratorUserConfiguresRouteView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mRouteGeneratorUserConfiguresRouteView = RouteGeneratorUserConfiguresRouteView(inflater, container, savedInstanceState, requireContext())
        return mRouteGeneratorUserConfiguresRouteView.getRootView()
    }

    override fun generateButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun navigateButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        mRouteGeneratorUserConfiguresRouteView.registerListener(this)
        super.onStart()
    }

    override fun onStop() {
        mRouteGeneratorUserConfiguresRouteView.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroy() {
        mRouteGeneratorUserConfiguresRouteView.unregisterListener(this)
        super.onDestroy()
    }
}