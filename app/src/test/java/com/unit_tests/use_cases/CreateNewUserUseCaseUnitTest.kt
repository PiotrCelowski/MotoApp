package com.unit_tests.use_cases

import com.motoappcleancodemvc.domain.entities.MotoAppEntityFactory
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository
import com.motoappcleancodemvc.domain.use_cases.CreateNewUserUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CreateNewUserUseCaseUnitTest {
    private lateinit var mMotoAppEntityFactoryMock: MotoAppEntityFactory
    private lateinit var mUserRepositoryMock: UserRepository
    private lateinit var mCreateNewUserUseCaseMock: CreateNewUserUseCase

    @Before
    fun setup() {
        mMotoAppEntityFactoryMock = Mockito.mock(MotoAppEntityFactory::class.java)
        mUserRepositoryMock = Mockito.mock(UserRepository::class.java)
        mCreateNewUserUseCaseMock = CreateNewUserUseCase(mMotoAppEntityFactoryMock, mUserRepositoryMock)
    }

    @Test
    fun should_CreateNewUserEntity_When_CreateNewUserAndNotify() {
        //given
        val mUserName = "TestUser"
        val mId = 12345
        val mExpectedUser = UserEntity(mUserName, mId)
        Mockito.`when`(
            mMotoAppEntityFactoryMock.getUserEntity(
                Mockito.anyString(),
                Mockito.anyInt()
            )
        ).thenReturn(mExpectedUser)

        //when
        mCreateNewUserUseCaseMock.createNewUserAndNotify(mUserName, mId)

        //then

    }
}