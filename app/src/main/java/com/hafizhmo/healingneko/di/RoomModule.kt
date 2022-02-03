package com.hafizhmo.healingneko.di

import android.content.Context
import androidx.room.Room
import com.hafizhmo.healingneko.data.local.FactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, FactDatabase::class.java, "fact-db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFactDao(db: FactDatabase) = db.factDao()

}