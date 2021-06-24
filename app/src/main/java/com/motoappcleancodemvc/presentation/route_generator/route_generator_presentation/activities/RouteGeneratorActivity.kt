package com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.motoappcleancodemvc.R
import com.motoappcleancodemvc.databinding.ActivityRouteGeneratorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteGeneratorActivity : AppCompatActivity() {
    private lateinit var mActivityRouteGeneratorBinding: ActivityRouteGeneratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityRouteGeneratorBinding = DataBindingUtil.setContentView(this, R.layout.activity_route_generator)
    }
}