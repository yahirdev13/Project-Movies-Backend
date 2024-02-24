package mx.com.utez.projectmoviesbackend.service;

import mx.com.utez.projectmoviesbackend.model.Genre;
import mx.com.utez.projectmoviesbackend.repository.GenreRepository;
import mx.com.utez.projectmoviesbackend.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public CustomResponse<List<Genre>> getGenres(){
        CustomResponse<List<Genre>> response = new CustomResponse<>();
        List<Genre> genres = genreRepository.findAll();
        if(!genres.isEmpty()){
            response.setData(genres);
            response.setError(false);
            response.setMessage("Genres found");
            response.setCode(200);
        } else {
            response.setData(null);
            response.setError(true);
            response.setMessage("No genres found");
            response.setCode(404);
        }
        return response;
    }
}
