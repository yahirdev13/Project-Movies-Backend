package mx.com.utez.projectmoviesbackend.repository;


import mx.com.utez.projectmoviesbackend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GenreRepository  extends JpaRepository<Genre, Long> {
}
