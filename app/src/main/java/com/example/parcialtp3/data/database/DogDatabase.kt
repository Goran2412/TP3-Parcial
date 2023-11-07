package com.example.parcialtp3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.util.Converters

@Database(entities = [DogModel::class], version = 2)
@TypeConverters(Converters::class)
abstract class DogDatabase: RoomDatabase() {
    abstract fun getDogDao(): DogDao
}