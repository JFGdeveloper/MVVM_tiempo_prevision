package com.javidev.eltiempo.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javidev.eltiempo.data.model.Favorite
import com.javidev.eltiempo.data.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
