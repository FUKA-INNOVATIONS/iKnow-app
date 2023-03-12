package com.fuka.iknow.data.database.converter

import androidx.room.TypeConverter
import java.util.Date


/*
* Time stamp convertor -> ROOM
* */

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}