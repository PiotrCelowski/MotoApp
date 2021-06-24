package com.motoappcleancodemvc.application.services.user_repository

import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {
    override fun createNewUser(mUser: UserEntity) {
        //Create new user in firebase
        //Create new user in sqllite database
    }

    override fun deleteUser(mUser: UserEntity) {
//        TODO("Not yet implemented")
    }

}