package org.company.annamedvedieva.imagebrowser.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImage(image: ImageItem)

    @Query("SELECT * FROM images_table")
    fun loadFavourites(): Flow<List<ImageItem>>

    @Query("SELECT * FROM images_table WHERE id=:id ")
    fun getImageById(id: String): Flow<ImageItem>

    @Delete
    fun deletePhoto(image: ImageItem)
}