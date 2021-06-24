package com.motoappcleancodemvc.domain.entities

import android.location.Location

data class UserEntity(val mLogin: String, val id: Int) {
    lateinit var userLocation: Location
}

