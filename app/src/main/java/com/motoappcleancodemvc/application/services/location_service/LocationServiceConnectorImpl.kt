package com.motoappcleancodemvc.application.services.location_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationServiceConnectorImpl @Inject constructor(@ApplicationContext private val context: Context) :
    LocationServiceConnector {
    private var mForegroundOnlyLocationServiceProvider: LocationServiceProvider? = null
    private var mForegroundOnlyLocationServiceBound = false
    private val serviceIntent = Intent(context, LocationServiceProvider::class.java)

    private val mForegroundOnlyServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as LocationServiceProvider.LocalBinder
            mForegroundOnlyLocationServiceProvider = binder.serviceProvider
            mForegroundOnlyLocationServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mForegroundOnlyLocationServiceProvider = null
            mForegroundOnlyLocationServiceBound = false
        }
    }

    override fun bindLocationService() {
        context?.bindService(serviceIntent, mForegroundOnlyServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun stopLocationService() {
        if (mForegroundOnlyLocationServiceBound) {
            context?.unbindService(mForegroundOnlyServiceConnection)
            mForegroundOnlyLocationServiceBound = false
        }
    }

    override fun stopLocationUpdates() {
        mForegroundOnlyLocationServiceProvider?.stopLocationUpdates()
    }

    override fun startLocationUpdates() {
        mForegroundOnlyLocationServiceProvider?.startLocationUpdates()
    }
}