package com.meretas.movielisto.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.meretas.movielisto.data.MovieDataMin

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(movieData: MovieDataMin)

    @Update
     fun update(movieData: MovieDataMin)

    @Query("SELECT * FROM movie_table WHERE id = :key")
     fun get(key : Int): LiveData<MovieDataMin>

    @Query("DELETE FROM movie_table")
     fun clear()

    @Query("DELETE FROM movie_table WHERE id = :key")
     fun delete(key : Int)

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
     fun getMovies(): List<MovieDataMin>

}