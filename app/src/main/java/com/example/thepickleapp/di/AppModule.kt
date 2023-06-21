package com.example.thepickleapp.di

import android.app.Application
import com.example.thepickleapp.BuildConfig
import com.example.thepickleapp.data.repo.CharacterRepositoryImplementation
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.domain.repo.CharacterRepository
import com.example.thepickleapp.domain.use_cases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        rickAndMortyApi: RickAndMortyApi, app: Application
    ): CharacterRepository {
        return CharacterRepositoryImplementation(
            rickAndMortyApi = rickAndMortyApi, appContext = app
        )
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }
}