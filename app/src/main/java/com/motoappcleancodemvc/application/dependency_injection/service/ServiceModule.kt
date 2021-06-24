package com.motoappcleancodemvc.application.dependency_injection.service

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.motoappcleancodemvc.presentation.notifications.NotificationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ServiceComponent::class)
abstract class ServiceModule {

    companion object {
        @ServiceScoped
        @Provides
        fun mFusedLocationProviderClient(@ApplicationContext appContext: Context): FusedLocationProviderClient {
            return LocationServices.getFusedLocationProviderClient(appContext)
        }

        @ServiceScoped
        @Provides
        fun mLocationRequest(): LocationRequest {
            return LocationRequest.create().apply {
                interval = TimeUnit.SECONDS.toMillis(2)
                fastestInterval = TimeUnit.SECONDS.toMillis(1)
                maxWaitTime = TimeUnit.MINUTES.toMillis(2)
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
        }

        @ServiceScoped
        @Provides
        fun mNotificationHelper(@ApplicationContext appContext: Context): NotificationHelper {
            return NotificationHelper(appContext)
        }
    }
}