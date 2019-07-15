package com.meretas.movielisto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.meretas.movielisto.data.MovieDataMin

@Database(entities = [MovieDataMin::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDatabaseDao: MovieDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstane(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }

}