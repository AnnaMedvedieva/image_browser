package org.company.annamedvedieva.imagebrowser.di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.company.annamedvedieva.imagebrowser.data.ImageDao
import org.company.annamedvedieva.imagebrowser.data.ImageDatabase
import org.company.annamedvedieva.imagebrowser.data.DefaultImageRepository
import org.company.annamedvedieva.imagebrowser.data.ImageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ImageDatabase::class.java,
            "image_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: ImageDatabase): ImageDao {
        return db.imageDao()
    }

    @Singleton
    @Provides
    fun provideRepository(db: ImageDatabase): ImageRepository {
        return DefaultImageRepository(db.imageDao())
    }

}