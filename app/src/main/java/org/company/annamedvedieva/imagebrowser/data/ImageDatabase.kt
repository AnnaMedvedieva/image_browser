package org.company.annamedvedieva.imagebrowser.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ImageItem::class), version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

}