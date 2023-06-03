package com.punyacile.challenge_chapter6.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MoviePopularTest{

    @Test
    fun testMoviePopularPage(){
        val moviePopular = MoviePopular (page = 1, results = emptyList(), totalPages = 10, totalResults = 1)
        assertEquals( 1, moviePopular.page)
    }

    @Test
    fun testMoviePopularResults() {
        val result1 = Result(id = 1, title = "Movie 1", adult = false)
        val result2 = Result(id = 2, title = "Movie 2", adult = false)
        val results = listOf(result1, result2)
        val moviePopular = MoviePopular(page = 1, results = results, totalPages = 10, totalResults = 100)
        assertEquals(results, moviePopular.results)
    }

    @Test
    fun testMoviePopularTotalPages(){

        val moviePopular = MoviePopular(page = 1, results = emptyList(), totalPages = 10, totalResults = 1)
        assertEquals(10, moviePopular.totalPages)
    }

    @Test
    fun testMoviePopularTotalResults(){
        MoviePopular(page = 1, results = emptyList(), totalPages = 10, totalResults = 1)
    }

    @Test
    fun testMoviePopularEmptyResults(){
        MoviePopular(page = 1, results = emptyList(), totalPages = 10, totalResults = 1)
    }

    data class MoviePopular(
        val page: Int,
        val results : List<Result>,
        val totalPages: Int,
        val totalResults: Int
    )

    data class Result(
        val id: Int,
        val title: String,
        val adult: Boolean
        )
}