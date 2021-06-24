package com.motoappcleancodemvc.domain.use_cases

import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository

class DeleteUserUseCase(private val mUserRepository: UserRepository) : BaseObservable<DeleteUserUseCase.Listener>() {

    interface Listener {
        fun onDeleteUser()
    }

    fun deleteUserAndNotify(mUser: UserEntity) {
        mUserRepository.deleteUser(mUser)
        notifyListeners()
    }

    fun notifyListeners() {
        for(listener in getListeners()) {
            listener.onDeleteUser()
        }
    }

}