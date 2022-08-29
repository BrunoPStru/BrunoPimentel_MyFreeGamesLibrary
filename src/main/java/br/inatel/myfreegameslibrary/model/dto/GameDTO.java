package br.inatel.myfreegameslibrary.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String thumbnail;

    private String short_description;

    private String game_url;

    @NotNull
    private Map<Long, String> genres;

    @NotNull
    private Map<Long, String> platforms;

    private String publisher;

    private String developer;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate release_date;

    private String freetogame_profile_url;

}
