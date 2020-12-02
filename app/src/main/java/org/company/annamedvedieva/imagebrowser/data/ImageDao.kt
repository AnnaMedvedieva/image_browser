package org.company.annamedvedieva.imagebrowser.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert
    fun insertImage(image: ImageItem)

    @Query("SELECT * FROM images_table")
    fun loadFavourites(): Flow<List<ImageItem>>

    @Delete
    fun deletePhoto(image: ImageItem)
}