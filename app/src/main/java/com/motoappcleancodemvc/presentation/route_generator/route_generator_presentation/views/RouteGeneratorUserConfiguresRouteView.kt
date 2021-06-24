package com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.motoappcleancodemvc.databinding.FragmentConfigurationMenuBinding
import com.motoappcleancodemvc.presentation.common.view.BaseView
import com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.fragments.RouteGeneratorUserConfiguresRouteFragment

class RouteGeneratorUserConfiguresRouteView (
        private val layoutInflater: LayoutInflater,
        private val parent: ViewGroup?,
        private val savedInstanceState: Bundle?,
        private val context: Context
): BaseView<RouteGeneratorUserConfiguresRouteView.Listener>() {
    open interface Listener {
        fun generateButtonClicked()
        fun navigateButtonClicked()
    }

    private val mFragmentConfigurationMenuBinding: FragmentConfigurationMenuBinding
    private var mRouteGeneratorUserConfiguresRouteFragment: RouteGeneratorUserConfiguresRouteFragment

    init {
        mFragmentConfigurationMenuBinding = FragmentConfigurationMenuBinding.inflate(layoutInflater, parent, false)

        mRouteGeneratorUserConfiguresRouteFragment = RouteGeneratorUserConfiguresRouteFragment()

        mFragmentConfigurationMenuBinding.generateRouteButton.setOnClickListener {
            for(listener in mListeners) {
                listener.generateButtonClicked()
            }
        }

        mFragmentConfigurationMenuBinding.navigationButton.setOnClickListener {
            for(listener in mListeners) {
                listener.navigateButtonClicked()
            }
        }

        mFragmentConfigurationMenuBinding.distanceBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(distanceBar: SeekBar?, progress: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                TODO("Not yet implemented")
            }

        })

        mFragmentConfigurationMenuBinding.northButton.setOnClickListener {

        }

        mFragmentConfigurationMenuBinding.southButton.setOnClickListener {

        }

        mFragmentConfigurationMenuBinding.eastButton.setOnClickListener {

        }

        mFragmentConfigurationMenuBinding.westButton.setOnClickListener {

        }

    }

    fun getRootView(): View {
        return mFragmentConfigurationMenuBinding.root
    }
}