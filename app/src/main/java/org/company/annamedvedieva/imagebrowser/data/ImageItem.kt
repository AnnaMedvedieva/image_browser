package org.company.annamedvedieva.imagebrowser.data

import androidx.room.*
import com.squareup.moshi.Json

@Entity(tableName = "images_table")
data class ImageItem(
    @PrimaryKey
    @Json(name = "id")
    var id: String,
    @Embedded
    @Json(name = "urls")
    var imageUrl: ImageUrls,
    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false
)

data class ImageUrls(
    @ColumnInfo(name = "small")
    @Json(name = "small")
    var smallSize: String,
    @ColumnInfo(name = "regular")
    @Json(name = "regular")
    var regularSize: String
)

data class SearchResults(
    @Json(name = "results")
    var results: List<ImageItem>
)


