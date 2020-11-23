package org.company.annamedvedieva.imagebrowser.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class ImageItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "image_url")
    var imageUrl: String,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false
)