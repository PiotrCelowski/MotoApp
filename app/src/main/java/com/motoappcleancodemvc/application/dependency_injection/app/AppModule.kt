package com.motoappcleancodemvc.application.dependency_injection.app

import com.motoappcleancodemvc.application.services.location_service.LocationServiceConnector
import com.motoappcleancodemvc.application.services.location_service.LocationServiceConnectorImpl
import com.motoappcleancodemvc.application.services.moto_app_service.MotoAppRepositoryImpl
import com.motoappcleancodemvc.application.services.response_adapter.MotoAppResponseAdapterImpl
import com.motoappcleancodemvc.application.services.url_builder.MotoAppUrlBuilderServiceImpl
import com.motoappcleancodemvc.application.services.user_repository.UserRepositoryImpl
import com.motoappcleancodemvc.domain.interfaces.MotoAppRepository
import com.motoappcleancodemvc.domain.interfaces.MotoAppResponseAdapter
import com.motoappcleancodemvc.domain.interfaces.MotoAppUrlBuilderService
import com.motoappcleancodemvc.domain.interfaces.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(mUserRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindServiceConnector(mLocationServiceConnectorImpl: LocationServiceConnectorImpl): LocationServiceConnector

    @Singleton
    @Binds
    abstract fun bindMotoAppRepository(mMotoAppRepositoryImpl: MotoAppRepositoryImpl): MotoAppRepository

    @Singleton
    @Binds
    abstract fun bindMotoAppResponseAdapter(mMotoAppResponseAdapterImpl: MotoAppResponseAdapterImpl): MotoAppResponseAdapter

    @Singleton
    @Binds
    abstract fun bindMotoAppUrlBuilderService(mMotoAppUrlBuilderServiceImpl: MotoAppUrlBuilderServiceImpl): MotoAppUrlBuilderService
}