package com.example.parcialtp3.di

import android.animation.TypeConverter
import android.content.Context
import androidx.room.Room
import com.example.parcialtp3.data.database.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val FACTURA_DATABASE_NAME = "dog_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogDatabase::class.java, FACTURA_DATABASE_NAME).build()
    @Singleton
    @Provides
    fun provideQuoteDao(db: DogDatabase) = db.getDogDao()
}