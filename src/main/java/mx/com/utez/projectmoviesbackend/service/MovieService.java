package mx.com.utez.projectmoviesbackend.service;

import jakarta.persistence.EntityNotFoundException;
import mx.com.utez.projectmoviesbackend.model.Movie;
import mx.com.utez.projectmoviesbackend.model.Genre;
import mx.com.utez.projectmoviesbackend.repository.GenreRepository;
import mx.com.utez.projectmoviesbackend.repository.MovieRepository;
import mx.com.utez.projectmoviesbackend.utils.CustomResponse;
import mx.com.utez.projectmoviesbackend.utils.DateBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public CustomResponse<List<Movie>> getMovies(){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findAll();
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<Movie> getMovieById(Long id){
        CustomResponse<Movie> response = new CustomResponse<>();
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent()){
            response.setData(movie.get());
            response.setError(false);
            response.setMessage("Movie found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("Movie not found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<List<Movie>> getMoviesByName(String name){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findByName(name);
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<List<Movie>> getMoviesByDirector(String director){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findByDirector(director);
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<List<Movie>> getMoviesBetweenDates(DateBody date){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findBetweenDates(date.getStartDate(), date.getEndDate());
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<List<Movie>> getMoviesByGenre(Long id){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findByGenre(id);
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<List<Movie>> getMoviesByReleaseDate(Date date){
        CustomResponse<List<Movie>> response = new CustomResponse<>();
        List<Movie> movies = movieRepository.findByReleaseDate(date);
        if(!movies.isEmpty()){
            response.setData(movies);
            response.setError(false);
            response.setMessage("Movies found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No movies found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<Movie> updateMovie(Long id, Movie movie){
        CustomResponse<Movie> response = new CustomResponse<>();
        Optional<Movie> movieFound = movieRepository.findById(id);
        if(movieFound.isPresent()){
            movie.setIdMovie(id);
            movieRepository.save(movie);
            response.setData(movie);
            response.setError(false);
            response.setMessage("Movie updated successfully");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("Movie not found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<Movie> saveMovie(Movie movie){
        Genre genre = movie.getGenre();
        CustomResponse<Movie> response = new CustomResponse<>();
        Genre genreFound = genreRepository.findById(genre.getIdGenre()).orElse(null);
        if(genre != null){
            movieRepository.save(movie);
            movie.setGenre(genreFound);
            response.setData(movie);
            response.setError(false);
            response.setMessage("Movie saved successfully");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("Genre not found");
            response.setCode(404);
        }
        return response;
    }

    public CustomResponse<Movie> deleteMovie(Long id){
        CustomResponse<Movie> response = new CustomResponse<>();
        try {
            movieRepository.deleteById(id);
            response.setData(null);
            response.setError(false);
            response.setMessage("Movie deleted successfully");
            response.setCode(200);
            return response;
        } catch (EntityNotFoundException e){
            response.setData(null);
            response.setError(true);
            response.setMessage("Movie not found");
            response.setCode(404);
            return response;
        }
    }
}
