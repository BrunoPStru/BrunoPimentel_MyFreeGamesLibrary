package br.inatel.myfreegameslibrary.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String thumbnail;

    private String short_description;

    private String game_url;

    @ManyToMany(mappedBy = "game")
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "game")
    private List<Platform> platforms = new ArrayList<>();

    private String publisher;

    private String developer;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate release_date;

    private String freetogame_profile_url;

}
