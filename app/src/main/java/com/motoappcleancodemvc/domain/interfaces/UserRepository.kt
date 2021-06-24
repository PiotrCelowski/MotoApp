package com.motoappcleancodemvc.domain.interfaces

import com.motoappcleancodemvc.domain.entities.UserEntity

interface UserRepository {
    fun createNewUser(mUser: UserEntity)
    fun deleteUser(mUser: UserEntity)
}