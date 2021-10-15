package com.example.mobirollertask.di

import com.example.mobirollertask.models.remote.FirebaseService
import com.example.mobirollertask.models.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
class NetworkModule {

    @Provides
    fun provideRemoteDataSource(
        firebaseService: FirebaseService,
    ): RemoteDataSource {
        return RemoteDataSource(firebaseService)
    }

    @Provides
    fun provideFirebaseService(): FirebaseService {
        return FirebaseService()
    }
}