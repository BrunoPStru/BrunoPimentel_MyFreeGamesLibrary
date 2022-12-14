package br.inatel.myfreegameslibrary.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String thumbnail;

    private String short_description;

    private String game_url;

    @NotNull
    @NotEmpty
    private String genre;

    private String platform;

    private String publisher;

    private String developer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate release_date;

    private String freetogame_profile_url;

}
