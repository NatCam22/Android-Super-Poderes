package com.example.androidsp.di

import android.content.Context
import androidx.room.Room
import com.example.androidsp.data.local.LocalDataSource
import com.example.androidsp.data.local.LocalDataSourceImpl
import com.example.androidsp.data.local.db.HeroDao
import com.example.androidsp.data.local.db.HeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesHeroDatabase(@ApplicationContext context: Context): HeroDatabase {
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "hero-database"
        ).build()
    }

    @Provides
    fun providesHeroDao(db: HeroDatabase): HeroDao {
        return db.heroDao()
    }

    @Provides
    fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource {
        return localDataSourceImpl
    }
}