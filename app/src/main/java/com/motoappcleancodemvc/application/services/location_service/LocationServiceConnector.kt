package com.motoappcleancodemvc.application.services.location_service

interface LocationServiceConnector {
    fun bindLocationService()
    fun stopLocationService()
    fun stopLocationUpdates()
    fun startLocationUpdates()
}