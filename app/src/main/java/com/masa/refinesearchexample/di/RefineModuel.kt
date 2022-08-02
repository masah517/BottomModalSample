package com.masa.refinesearchexample.di

import com.masa.refinesearchexample.data.RefineParameterRepository
import com.masa.refinesearchexample.data.RefineParameterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RefineModule{

    @Provides
    @ActivityRetainedScoped
    fun provideRefineParameterRepository(): RefineParameterRepository {
        return RefineParameterRepositoryImpl()
    }
}
