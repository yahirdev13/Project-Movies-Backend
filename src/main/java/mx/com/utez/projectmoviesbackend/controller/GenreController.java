package mx.com.utez.projectmoviesbackend.controller;


import mx.com.utez.projectmoviesbackend.model.Genre;
import mx.com.utez.projectmoviesbackend.service.GenreService;
import mx.com.utez.projectmoviesbackend.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin(origins = "*")
public class GenreController {
    @Autowired
    private GenreService service;

    @GetMapping
    public CustomResponse<List<Genre>> getGenres(){
        return service.getGenres();
    }
}
