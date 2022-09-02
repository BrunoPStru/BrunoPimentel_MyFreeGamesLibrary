package br.inatel.myfreegameslibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String genre;

}
