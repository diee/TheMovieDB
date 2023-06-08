package com.example.themoviedb.domain

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val mainRepository: MainRepository) {
    operator fun invoke(page: Int) = mainRepository.getMovies(page)
}