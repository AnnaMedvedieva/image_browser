package org.company.annamedvedieva.imagebrowser.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.company.annamedvedieva.imagebrowser.UnsplashApiKey
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.SearchResults
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.unsplash.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val client = OkHttpClient.Builder()
    .addInterceptor(HeaderInterceptor(UnsplashApiKey.API_ACCESS_KEY))
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface BrowserApiService {
    @GET("photos/random")
    suspend fun getRandomPictures(@Query("count") count: Int): List<ImageItem>

    @GET("search/photos")
    suspend fun getSearchResults(
        @Query("query") searchQuery: String,
        @Query("per_page") photosPerPage: Int,
        @Query("page") pageNumber: Int
    ): SearchResults
}


object BrowserApi {
    val retrofitService: BrowserApiService by lazy {
        retrofit.create(BrowserApiService::class.java)
    }
}

