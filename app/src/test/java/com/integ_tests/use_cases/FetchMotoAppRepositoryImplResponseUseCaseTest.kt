package com.integ_tests.use_cases

import com.motoappcleancodemvc.domain.entities.MotoAppEntityFactory
import com.motoappcleancodemvc.domain.entities.NavigationInputEntity
import com.motoappcleancodemvc.domain.entities.RouteGeometryEntity
import com.motoappcleancodemvc.domain.interfaces.MotoAppRepository
import com.motoappcleancodemvc.domain.interfaces.MotoAppResponseAdapter
import com.motoappcleancodemvc.domain.interfaces.MotoAppUrlBuilderService
import com.motoappcleancodemvc.domain.use_cases.FetchMotoAppServiceResponseUseCase
import com.motoappcleancodemvc.application.services.response_adapter.MotoAppServiceResponseSchema
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class FetchMotoAppRepositoryImplResponseUseCaseTest {

    private lateinit var mMotoAppRepositoryMock: MotoAppRepository
    private lateinit var mMotoAppResponseAdapterMock: MotoAppResponseAdapter
    private lateinit var mMotoAppEntityFactoryMock: MotoAppEntityFactory
    private lateinit var mMotoAppUrlBuilderServiceMock: MotoAppUrlBuilderService
    private lateinit var mMotoAppServiceUrl: String
    private lateinit var mFetchMotoAppServiceResponseUseCase: FetchMotoAppServiceResponseUseCase
    private lateinit var mListener: FetchMotoAppServiceResponseUseCase.Listener
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private lateinit var jsonAdapter: JsonAdapter<MotoAppServiceResponseSchema>

    @Before
    fun setup() {
        mMotoAppEntityFactoryMock = mock(MotoAppEntityFactory::class.java)
        mMotoAppRepositoryMock = mock(MotoAppRepository::class.java)
        mMotoAppResponseAdapterMock = mock(MotoAppResponseAdapter::class.java)
        mMotoAppUrlBuilderServiceMock = mock(MotoAppUrlBuilderService::class.java)
        mFetchMotoAppServiceResponseUseCase = FetchMotoAppServiceResponseUseCase(mMotoAppRepositoryMock, mMotoAppResponseAdapterMock, mMotoAppEntityFactoryMock)
        mListener = spy(FetchMotoAppServiceResponseUseCase.Listener::class.java)
        mFetchMotoAppServiceResponseUseCase.registerListener(mListener)
        jsonAdapter = moshi.adapter(MotoAppServiceResponseSchema::class.java)
    }

    @Test
    fun should_NotifyListenersWithNavigationInputAndRouteGeometry_When_fetchMotoAppServiceResponseAndNotify() {
        //given
        val classLoader = this.javaClass.classLoader
        val mExpectedNavigationInputURL = classLoader.getResource("exampleResponse")
        val mExpectedResponseJSON = mExpectedNavigationInputURL.readText(Charsets.UTF_8)
        val mExpectedNavigationInput = NavigationInputEntity(mExpectedResponseJSON)
        val mExpectedMotoAppServiceResponseSchema = jsonAdapter.fromJson(mExpectedResponseJSON)
        val mExpectedRouteGeometry = RouteGeometryEntity("1234abcd")

        `when`(mMotoAppRepositoryMock.fetchMotoAppServiceRoute(anyString())).thenReturn(mExpectedResponseJSON)
        `when`(mMotoAppResponseAdapterMock.convertJsonToRouteGeometry(anyString())).thenReturn(mExpectedMotoAppServiceResponseSchema)
        `when`(mMotoAppEntityFactoryMock.getNavigationInputEntity(anyString())).thenReturn(mExpectedNavigationInput)
        `when`(mMotoAppEntityFactoryMock.getRouteGeometryEntity(anyString())).thenReturn(mExpectedRouteGeometry)

        //when
        mFetchMotoAppServiceResponseUseCase.fetchMotoAppServiceResponseAndNotify("motoapp.com")

        //then
        verify(mListener, atLeastOnce()).onNavigationInputFetchSuccess(mExpectedNavigationInput, mExpectedRouteGeometry)
    }
}