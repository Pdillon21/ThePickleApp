package com.example.thepickleapp.di

import com.example.thepickleapp.BuildConfig
import com.example.thepickleapp.data.repo.CharacterRepositoryImplementation
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.repo.EpisodesRepositoryImplementation
import com.example.thepickleapp.data.repo.LocationsRepositoryImplementation
import com.example.thepickleapp.domain.repo.CharacterRepository
import com.example.thepickleapp.domain.repo.EpisodesRepository
import com.example.thepickleapp.domain.repo.LocationsRepository
import com.example.thepickleapp.domain.use_cases.GetCharactersUseCase
import com.example.thepickleapp.domain.use_cases.GetEpisodesUseCase
import com.example.thepickleapp.domain.use_cases.GetLocationsUseCase
import com.example.thepickleapp.domain.utils.Paginator
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
    fun provideCharacterRepository(
        rickAndMortyApi: RickAndMortyApi
    ): CharacterRepository {
        return CharacterRepositoryImplementation(
            rickAndMortyApi = rickAndMortyApi
        )
    }

    @Provides
    @Singleton
    fun provideLocationsRepository(
        rickAndMortyApi: RickAndMortyApi
    ): LocationsRepository {
        return LocationsRepositoryImplementation(
            rickAndMortyApi = rickAndMortyApi
        )
    }

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        rickAndMortyApi: RickAndMortyApi
    ): EpisodesRepository {
        return EpisodesRepositoryImplementation(
            rickAndMortyApi = rickAndMortyApi
        )
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(repository, Paginator)
    }

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(
        repository: EpisodesRepository
    ): GetEpisodesUseCase {
        return GetEpisodesUseCase(repository, Paginator)
    }

    @Provides
    @Singleton
    fun provideGetLocationsUseCase(
        repository: LocationsRepository
    ): GetLocationsUseCase {
        return GetLocationsUseCase(repository, Paginator)
    }
}