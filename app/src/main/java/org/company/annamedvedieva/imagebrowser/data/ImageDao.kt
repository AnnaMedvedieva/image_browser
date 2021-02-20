package org.company.annamedvedieva.imagebrowser.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: ImageItem)

    @Query("SELECT * FROM images_table")
    fun loadFavourites(): Flow<List<ImageItem>>

    @Query("SELECT * FROM images_table WHERE id=:id ")
    fun getImageById(id: String): Flow<ImageItem>

    @Delete
    suspend fun deletePhoto(image: ImageItem)

    @Update
    suspend fun updateImage(image: ImageItem)
}