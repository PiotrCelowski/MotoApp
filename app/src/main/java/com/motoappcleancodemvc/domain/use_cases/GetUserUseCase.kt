package com.motoappcleancodemvc.domain.use_cases

import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository

class GetUserUseCase(private val mUserRepository: UserRepository) : BaseObservable<DeleteUserUseCase.Listener>() {

    interface Listener {
        fun onGetUser()
    }

    fun getUserAndNotify(name: String) {


        notifyListeners()
    }

    fun notifyListeners() {
        for(listener in getListeners()) {
            listener.onDeleteUser()
        }
    }
}