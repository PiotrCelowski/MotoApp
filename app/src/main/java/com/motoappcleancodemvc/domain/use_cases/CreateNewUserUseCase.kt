package com.motoappcleancodemvc.domain.use_cases

import com.motoappcleancodemvc.domain.common.BaseObservable
import com.motoappcleancodemvc.domain.entities.MotoAppEntityFactory
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository
import javax.inject.Inject

class CreateNewUserUseCase @Inject constructor(private val mMotoAppEntityFactory: MotoAppEntityFactory,
                                               private val mUserRepository: UserRepository) : BaseObservable<CreateNewUserUseCase.Listener>() {

    interface Listener {
        fun onUserCreated(mUser: UserEntity)
    }

    fun createNewUserAndNotify(mLogin: String, id: Int) {
        val mUser = mMotoAppEntityFactory.getUserEntity(mLogin, id)
        mUserRepository.createNewUser(mUser)
        notifyListeners(mUser)
    }

    private fun notifyListeners(mUser: UserEntity) {
        for(listener in getListeners()) {
            listener.onUserCreated(mUser)
        }
    }


}