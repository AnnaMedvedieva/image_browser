package org.company.annamedvedieva.imagebrowser.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "images_table")
data class ImageItem(
    @PrimaryKey
    @Json(name = "id")
    var id: String,
    @ColumnInfo(name = "image_url")
    @Json(name = "urls")
    var imageUrl: ImageUrls,
    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false
)

data class ImageUrls(
    @Json(name = "small")
    var smallSize: String,
    @Json(name = "regular")
    var regularSize: String
)

data class SearchResults(
    @Json(name = "results")
    var results: List<ImageItem>
)


