package mx.com.utez.projectmoviesbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    private @Id @GeneratedValue Long idMovie;
    private String name, director, duration;
    private Date releaseDate;
    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genre genre;
}
