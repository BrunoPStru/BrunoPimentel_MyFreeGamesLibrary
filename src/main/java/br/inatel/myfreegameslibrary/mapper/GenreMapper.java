package br.inatel.myfreegameslibrary.mapper;

import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import br.inatel.myfreegameslibrary.model.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {

    public static List<GenreDTO> toGenreDTOList(List<Genre> genreList) {
        return genreList.stream().map(genre -> toGenreDTO(genre)).collect(Collectors.toList());
    }

    public static GenreDTO toGenreDTO(Genre genre) {

        GenreDTO genreDTO = GenreDTO.builder()
                .id(genre.getId())
                .genre(genre.getGenre())
                .build();

        return genreDTO;
    }
}
