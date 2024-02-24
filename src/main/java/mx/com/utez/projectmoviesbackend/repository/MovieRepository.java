package mx.com.utez.projectmoviesbackend.repository;

import mx.com.utez.projectmoviesbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    @Query("SELECT m FROM Movie m JOIN m.genre g")
    List<Movie> findAll();

    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) LIKE LOWER(concat('%', :name, '%'))")
    List<Movie> findByName(String name);

    @Query("SELECT m FROM Movie m WHERE LOWER(m.director) LIKE LOWER(concat('%', :director, '%'))")
    List<Movie> findByDirector(String director);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate BETWEEN :start AND :end")
    List<Movie> findBetweenDates(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT m FROM Movie m JOIN m.genre g WHERE g.idGenre = :id")
    List<Movie> findByGenre(Long id);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate = :date ORDER BY m.releaseDate DESC")
    List<Movie> findByReleaseDate(Date date);
}
