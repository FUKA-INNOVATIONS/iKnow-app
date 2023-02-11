package com.fuka.iknow.data.database.converter

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    suspend fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    suspend fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}