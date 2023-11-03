package com.example.parcialtp3.data.database

import android.animation.TypeConverter
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.data.model.DogModel

@Database(entities = [DogModel::class], version = 1)
abstract class DogDatabase: RoomDatabase() {
    abstract fun getDogDao(): DogDao
}