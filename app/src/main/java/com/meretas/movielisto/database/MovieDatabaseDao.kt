package com.meretas.movielisto.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.meretas.movielisto.data.MovieListData

@Dao
interface MovieDatabaseDao {

    @Insert
    fun insert(movieData: MovieListData.Result)

    @Update
    fun update(movieData: MovieListData.Result)

    @Query("SELECT * FROM movie_table WHERE id = :key")
    fun get(key : Int): MovieListData.Result

    @Query("DELETE FROM movie_table")
    fun clear()

    @Query("DELETE FROM movie_table WHERE id = :key")
    fun delete(key : Int)

    @Query("SELECT * FROM movie_table ORDER BY release_date DESC")
    fun getMovies(): LiveData<List<MovieListData.Result>>

}