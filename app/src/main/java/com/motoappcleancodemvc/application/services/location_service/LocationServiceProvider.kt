package com.motoappcleancodemvc.application.services.location_service

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.*
import android.util.Log
import com.google.android.gms.location.*
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.use_cases.CreateNewUserUseCase
import com.motoappcleancodemvc.domain.use_cases.UpdateUserLocationUseCase
import com.motoappcleancodemvc.presentation.notifications.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationServiceProvider(): Service(), CreateNewUserUseCase.Listener, UpdateUserLocationUseCase.Listener {
    @Inject lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    @Inject lateinit var mLocationRequest: LocationRequest
    @Inject lateinit var mNotification: NotificationHelper
    @Inject lateinit var mCreateNewUserUseCase: CreateNewUserUseCase
    @Inject lateinit var mUpdateUserLocationUseCase: UpdateUserLocationUseCase

    private lateinit var mLocationCallback: LocationCallback
    private var mCurrentLocation: Location? = null
    private val mLocalBinder = LocalBinder()
    private var mServiceRunningInForeground = false
    private lateinit var mUser: UserEntity
    private var mLocationServiceLooper: Looper? = null
    private var mLocationServiceHandler: Handler? = null
    private lateinit var mServiceThread: HandlerThread
    private val TAG = "LocationService"


    override fun onCreate() {
        /**
        *TODO()
        *Temporarily create data class here
        *Class will be used to keep location data up to data
        *Later it will be changed to Repository with SQLite DB
        **/
        mCreateNewUserUseCase.createNewUserAndNotify("celop", 0)

        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                //Updating location in data class happens here
                //Temporarily data class creation als happens here
                mCurrentLocation = locationResult.lastLocation
                mUpdateUserLocationUseCase.setUserCoordinatesAndNotify(mUser, mCurrentLocation!!)
                Log.i("Current location: ", mCurrentLocation?.latitude.toString() + ", " + mCurrentLocation?.longitude.toString())
                Log.i("Service thread: ", Thread.currentThread().name)
            }
        }

        if(mServiceRunningInForeground) {
            mNotification.createNotificationChannel();
            mNotification.createNotification();
        }

        //Thread stuff
        mServiceThread = HandlerThread("LocationServiceThread", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            mLocationServiceLooper = looper
            mLocationServiceHandler = Handler(looper)
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("$TAG onBind", Thread.currentThread().name)
        stopForeground(true)
        mServiceRunningInForeground = false
        return mLocalBinder
    }

    override fun onRebind(intent: Intent) {
        Log.i("$TAG onRebind", Thread.currentThread().name)
        stopForeground(true)
        mServiceRunningInForeground = false
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("$TAG onUnbind", Thread.currentThread().name)
        Log.i("LocationService", "unbinding activity")
        mServiceRunningInForeground = true
        mNotification.createNotificationChannel();
        startForeground(NOTIFICATION_ID, mNotification.createNotification())
        return true
    }

    override fun onDestroy() {
        Log.i("$TAG onDestroy", Thread.currentThread().name)
        Log.i("LocationService", "Destroying")
        mServiceThread.quit()
        super.onDestroy()
    }

    fun startLocationUpdates() {
        mLocationServiceHandler?.post {
            startService(Intent(applicationContext, LocationServiceProvider::class.java))
            try {
                mFusedLocationProviderClient.requestLocationUpdates(
                    mLocationRequest,
                    mLocationCallback,
                    mLocationServiceLooper
                )
            } catch (unlikely: SecurityException) {
                TODO("Not yet implemented")
            }
        }
    }

    fun stopLocationUpdates() {
        mLocationServiceHandler?.post {
            try {
                val removeTask = mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
                removeTask.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        stopSelf()
                    } else {
                        TODO("Not yet implemented")
                    }
                }

            } catch (unlikely: SecurityException) {
                TODO("Not yet implemented")
            }
        }
    }

    inner class LocalBinder : Binder() {
        internal val serviceProvider: LocationServiceProvider
            get() = this@LocationServiceProvider
    }

    companion object {
        private const val NOTIFICATION_ID = 12345678
    }

    override fun onUserCreated(mUser: UserEntity) {
        this.mUser = mUser
    }

    override fun onUserCoordinatesChanged(mUser: UserEntity) {
        TODO("Not yet implemented")
    }
}