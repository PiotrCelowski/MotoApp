package com.integ_tests.use_cases

import com.motoappcleancodemvc.domain.entities.MotoAppEntityFactory
import com.motoappcleancodemvc.domain.entities.UserEntity
import com.motoappcleancodemvc.domain.interfaces.UserRepository
import com.motoappcleancodemvc.domain.use_cases.CreateNewUserUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CreateNewUserUseCaseTest {

    private lateinit var mMotoAppEntityFactoryMock: MotoAppEntityFactory
    private lateinit var mUserRepositoryMock: UserRepository
    private lateinit var mCreateNewUserUseCaseMock: CreateNewUserUseCase
    private lateinit var mListenerSpy: CreateNewUserUseCase.Listener

    @Before
    fun setup() {
        mMotoAppEntityFactoryMock = mock(MotoAppEntityFactory::class.java)
        mUserRepositoryMock = mock(UserRepository::class.java)
        mCreateNewUserUseCaseMock = CreateNewUserUseCase(mMotoAppEntityFactoryMock, mUserRepositoryMock)
        mListenerSpy = spy(CreateNewUserUseCase.Listener::class.java)
    }


    @Test
    fun should_NotifyListenersWithUserEntity_When_CreateNewUserAndNotify() {
        //given
        val mUserName = "TestUser"
        val mId = 12345
        val mExpectedUser = UserEntity(mUserName, mId)
        mCreateNewUserUseCaseMock.registerListener(mListenerSpy)
        `when`(mMotoAppEntityFactoryMock.getUserEntity(anyString(), anyInt())).thenReturn(mExpectedUser)

        //when
        mCreateNewUserUseCaseMock.createNewUserAndNotify(mUserName, mId)

        //then
        verify(mListenerSpy, times(1)).onUserCreated(mExpectedUser)

    }

}