package com.unit_tests.use_cases

import android.location.Location
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.use_cases.UpdateUserLocationUseCase
import org.junit.Before
import org.junit.Test

class UpdateUserLocationUseCaseTest {
    private lateinit var mUpdateUserLocationUseCase: UpdateUserLocationUseCase

    @Before
    fun setup() {
        mUpdateUserLocationUseCase = UpdateUserLocationUseCase()
    }

    @Test
    fun should_UpdateUserEntity_When_SetUserCoordinatesAndNotify() {
        //given
        val mUserName = "TestUser"
        val mId = 12345
        val mUser = UserEntity(mUserName, mId)
        val mLongitude: Double = 49.36703842110475
        val mLatitude: Double = 19.924044230685407
        val mLocation: Location = Location("FakeProvider")
        mLocation.longitude = mLongitude
        mLocation.latitude = mLatitude

        //when
        mUpdateUserLocationUseCase.setUserCoordinatesAndNotify(mUser, mLocation)

        //then
        assert(mUser.userLocation == mLocation)

    }

}