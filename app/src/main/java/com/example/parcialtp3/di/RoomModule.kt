package com.example.parcialtp3.di

import android.content.Context
import androidx.room.Room
import com.example.parcialtp3.data.database.DogDatabase
import com.example.parcialtp3.util.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DOG_DATABASE_NAME = "dog_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogDatabase::class.java, DOG_DATABASE_NAME)
            .addTypeConverter(Converters())
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun provideDogDao(db: DogDatabase) = db.getDogDao()
}